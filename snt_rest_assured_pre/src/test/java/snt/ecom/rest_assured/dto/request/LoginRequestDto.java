package snt.ecom.rest_assured.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @since 02/08/2021 19:42
 */

@Builder
@Data
public class LoginRequestDto {

    private String email;

    private String password;
}
