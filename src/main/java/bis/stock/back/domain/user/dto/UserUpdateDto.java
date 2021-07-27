package bis.stock.back.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private String email;
    private String password;
    private String nickname;

    public void encryptPassword(String password) {
        this.password = password;
    }
}
