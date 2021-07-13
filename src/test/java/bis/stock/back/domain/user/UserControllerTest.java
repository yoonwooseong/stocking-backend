package bis.stock.back.domain.user;

import bis.stock.back.domain.user.dto.UserUpdateDto;
import bis.stock.back.global.config.security.JwtTokenProvider;
import bis.stock.back.global.util.CookieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void createController() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        userRepository.save(User.builder()
                .email("test@test.com")
                .password(passwordEncoder.encode("1234"))
                .nickname("test")
                .cash(0L)
                .roles(Collections.singletonList(UserRole.ROLE_USER))
                .build());
    }

    @Test
    void 유저정보조회() throws Exception {

        User user = userRepository.findByEmail("test@test.com").orElse(null);
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
        Cookie accessCookie = new Cookie("accessToken", accessToken);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/" + user.getId())
                .cookie(accessCookie);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", user.getId()).exists())
                .andExpect(jsonPath("email", "test@test.com").exists())
                .andDo(print());
    }

    @Test
    void 유저수정() throws Exception {

        User user = userRepository.findByEmail("test@test.com").orElse(null);
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
        Cookie accessCookie = new Cookie("accessToken", accessToken);

        String content = objectMapper.writeValueAsString(
                UserUpdateDto.builder()
                        .email("test@test.com")
                        .password(passwordEncoder.encode("5678"))
                        .nickname("sang")
                        .build()
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user/" + user.getId())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(accessCookie);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", user.getId()).exists())
                .andExpect(jsonPath("email", "test@test.com").exists())
                .andExpect(jsonPath("password", passwordEncoder.encode("5678")).exists())
                .andExpect(jsonPath("nickname", "sang").exists())
                .andDo(print());
    }

    @Test
    void 유저삭제() throws Exception {

        User user = userRepository.findByEmail("test@test.com").orElse(null);
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
        Cookie accessCookie = new Cookie("accessToken", accessToken);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/" + user.getId())
                .cookie(accessCookie);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent())
                .andDo(print());

        user = userRepository.findByEmail("test@test.com").orElse(null);
        assertThat(user).isNull();
    }
}