package ecom.dto.buyer.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:14
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpResponseDto {

    private Boolean success;

    private String token;

    private String message;

}
