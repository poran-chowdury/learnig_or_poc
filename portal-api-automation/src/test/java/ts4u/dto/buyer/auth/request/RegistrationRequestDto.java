package ts4u.dto.buyer.auth.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegistrationRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String password;

}
