package bigdater.dater.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class SignUpUserRequest {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotNull(message = "성별을 선택해주세요.")
    private Boolean sex;

    @NotNull(message = "안밖을 선택해주세요.")
    private Boolean isInsider;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String freeTime;

    @NotEmpty(message = "동반자를 입력해주세요.")
    private String companion;

    @NotEmpty(message = "음식 종류를 입력해주세요.")
    private String food;

    @NotEmpty(message = "연령대를 선택해주세요.")
    private String age;
}
