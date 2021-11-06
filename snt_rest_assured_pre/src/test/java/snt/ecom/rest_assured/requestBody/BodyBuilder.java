package snt.ecom.rest_assured.requestBody;


import com.google.gson.Gson;
import snt.ecom.rest_assured.dto.request.*;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:12
 */

public class BodyBuilder {

    public String getRegistrationRequestBody(String name, String email, String password) {
        return new Gson().toJson(
            RegistrationRequestDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .build()
        );
    }

    public String getVerifyOtpRequestBody(String otp, String email) {
        return new Gson().toJson(
            VerifyOtpRequestDto.builder()
                .otp(otp)
                .email(email)
                .build()
        );
    }

    public String getLoginRequestBody(String email, String password) {
        return new Gson().toJson(
            LoginRequestDto.builder()
                .email(email)
                .password(password)
                .build()
        );
    }

}
