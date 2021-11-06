package payload;

import lombok.Builder;
import lombok.Setter;

@Builder(toBuilder = true)
@Setter
public class RegisterCompanyManagerPayload {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String username;
    private String company_id;
    private Integer member_type;
}
