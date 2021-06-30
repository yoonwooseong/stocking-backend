package bis.stock.back.domain.user;

import bis.stock.back.domain.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable Long id, HttpServletRequest req) {

        return ResponseEntity.ok(userService.getUserInfo(id, req));
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @RequestBody UserUpdateDto userUpdateDto,
                                       HttpServletRequest req) {

        return ResponseEntity.ok(userService.updateUser(id, userUpdateDto, req));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         HttpServletRequest req,
                                         HttpServletResponse res) {

        // 유저 서비스에서 삭제 메소드
        userService.deleteUser(id, req, res);
        // 삭제시 더 이상 표시할 값이 없기 때문에 no content 상태코드를 반환함.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletResponse res) {

        return ResponseEntity.ok(userService.logout(res));
    }
}
