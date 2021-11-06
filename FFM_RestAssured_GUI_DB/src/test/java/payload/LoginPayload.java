package payload;

import lombok.Builder;
import lombok.Setter;

@Builder(toBuilder = true)
@Setter
public class LoginPayload {
    private String email;
    private String password;
}
