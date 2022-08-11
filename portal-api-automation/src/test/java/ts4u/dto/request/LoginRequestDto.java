package ts4u.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequestDto {

    private String email;

    private String password;
}
