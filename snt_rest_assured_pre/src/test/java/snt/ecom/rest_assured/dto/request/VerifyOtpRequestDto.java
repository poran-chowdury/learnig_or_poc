package snt.ecom.rest_assured.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:14
 */

@Builder
@Data
public class VerifyOtpRequestDto {

    private String otp;

    private String email;

}
