package ts4u.dto.buyer.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResendOtpResponseDto {

    private Boolean isOtpSend;

    private String success;

}
