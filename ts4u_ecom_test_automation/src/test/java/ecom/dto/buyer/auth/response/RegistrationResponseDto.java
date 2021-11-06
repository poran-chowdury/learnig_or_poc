package ecom.dto.buyer.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 06:21
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDto {

    private Boolean isOtpSend;

    private String email;

    private String message;

}
