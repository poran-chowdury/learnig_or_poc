package payload;

import lombok.Builder;
import lombok.Setter;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:14
 */

@Builder(toBuilder = true)
@Setter
public class RegisterFMMPayload {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String username;
}
