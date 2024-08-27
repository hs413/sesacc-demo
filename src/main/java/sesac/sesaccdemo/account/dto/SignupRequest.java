package sesac.sesaccdemo.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "이메일이 입력되지 않았습니다.")
        @Email(message = "이메일 형식이 아닙니다")
        String email,

        @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$",
                message = "비밀번호는 8~20자 이내로 영어, 숫자, 특수문자를 포함해야 합니다."
        )
        String password,

        @NotBlank(message = "이름이 입력되지 않았습니다.")
        @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
        @Size(min = 1, max = 5, message = "이름은 1 ~ 5자로 입력해주세요.")
        String name,

        @Pattern(regexp = "^\\d+$", message = "숫자만 입력 가능합니다.")
        @Size(min = 6, max = 6, message = "주민번호 앞자리를 올바르게 입력해주세요")
        String birthDate,

        @Min(value = 1, message = "주민번호 뒷자리는 1 ~ 4의 값을 입력해주세요")
        @Max(value = 4, message = "주민번호 뒷자리는 1 ~ 4의 값을 입력해주세요")
        int gender,

        @NotNull(message = "과정을 선택해주세요")
        Long firstCourseId
) {

}
