package ecom.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegistrationRequestDto {
    private String name;
    private String email;
    private String password;
}
