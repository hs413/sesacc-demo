package sesac.sesaccdemo.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailCheckRequest(
        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식이 아닙니다")
        String email
) {

}
