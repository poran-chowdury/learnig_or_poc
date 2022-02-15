package ecom.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponseDto {

    private String token;
    private String message;
    private String user;
    private String role;
    private String email;
}
