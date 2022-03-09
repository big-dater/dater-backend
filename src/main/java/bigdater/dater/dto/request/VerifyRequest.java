package bigdater.dater.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class VerifyRequest {

    @NotEmpty(message = "코드를 입력해주세요.")
    private String verifyCode;

    @NotEmpty(message = "코드를 입력해주세요.")
    @Size(min = 6, max = 6, message = "유효한 코드를 입력해주세요.")
    private String userCode;
}
