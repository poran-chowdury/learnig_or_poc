package snt.ecom.rest_assured.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:14
 */

@Builder
@Data
public class RegistrationRequestDto {

    private String name;

    private String email;

    private String password;

}
