package ts4u.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDto {

    private String token;

    private String message;

    private String user;

    private String role;

    private String email;

}
