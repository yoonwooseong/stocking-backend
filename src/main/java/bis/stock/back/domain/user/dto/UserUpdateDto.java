package bis.stock.back.domain.user.dto;

import lombok.Getter;

@Getter
public class UserUpdateDto {

    private String email;
    private String password;
    private String nickname;

    public void encryptPassword(String password) {
        this.password = password;
    }
}
