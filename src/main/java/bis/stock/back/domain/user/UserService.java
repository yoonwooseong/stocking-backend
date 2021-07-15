package bis.stock.back.domain.user;

import bis.stock.back.domain.user.dto.UserUpdateDto;
import bis.stock.back.global.config.security.JwtTokenProvider;
import bis.stock.back.global.exception.NotFoundException;
import bis.stock.back.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final CookieUtil cookieUtil;

    public User getUserInfo(Long id, HttpServletRequest req) {

        // url에 들어온 id로 유저를 찾고, jwt access token에 담긴 email로 유저를 찾은 뒤 둘이 같은지 유효성 검사를 거침

        String token = jwtTokenProvider.resolveToken(req);
        User userById = userRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        User user = userRepository.findByEmail(jwtTokenProvider.getUserPk(token))
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        if(user != userById) {
            throw new NotFoundException("Access Token이 잘못 되었습니다.");
        }
        return user;
    }

    // 변경감지는 Transactional 사용해야함
    @Transactional
    public User updateUser(Long id, UserUpdateDto userUpdateDto, HttpServletRequest req) {

        String token = jwtTokenProvider.resolveToken(req);
        User userById = userRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        User user = userRepository.findByEmail(jwtTokenProvider.getUserPk(token))
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));

        if(user != userById) {
            throw new NotFoundException("Access Token이 잘못 되었습니다.");
        }

        // 비밀번호 암호화
        userUpdateDto.encryptPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        // 유저 탐색 후, 수정만 하고 저장을 따로 하지 않아도 알아서 저장 됨.
        // 자세한 사항은 JPA 변경감지 검색
        user.update(userUpdateDto);
        return user;
    }

    public void deleteUser(Long id, HttpServletRequest req, HttpServletResponse res) {

        String token = jwtTokenProvider.resolveToken(req);
        User userById = userRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        User user = userRepository.findByEmail(jwtTokenProvider.getUserPk(token))
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));
        if(user != userById) {
            throw new NotFoundException("Access Token이 잘못 되었습니다.");
        }

        // 쿠키 지우기
        cookieUtil.deleteCookie(res, "accessToken");
        cookieUtil.deleteCookie(res, "refreshToken");

        userRepository.delete(user);
    }

    public String logout(HttpServletResponse res) {

        // 쿠키 지우기
        cookieUtil.deleteCookie(res, "accessToken");
        cookieUtil.deleteCookie(res, "refreshToken");

        return "good bye";
    }
}
