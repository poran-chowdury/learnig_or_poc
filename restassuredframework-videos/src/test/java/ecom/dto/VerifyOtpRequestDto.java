package ecom.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VerifyOtpRequestDto {
    private String otp;
    private String email;
}
