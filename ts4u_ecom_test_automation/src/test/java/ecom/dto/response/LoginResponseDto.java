package ecom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 02/08/2021 19:43
 */

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
