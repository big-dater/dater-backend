package bigdater.dater.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VerifyResponse {
    private Boolean isValid;
}
