package ecom.requestBody;

import com.google.gson.Gson;
import ecom.dto.LoginRequestDto;
import ecom.dto.RegistrationRequestDto;
import ecom.dto.VerifyOtpRequestDto;

public class BodyBuilder {

    //Get Registration Request Body Information in Json Format
    public String getRegistrationRequestBody(String name, String email, String password ) {
        return new Gson().toJson(
                RegistrationRequestDto.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build()
        );
    }
    //Get Login Request Body Information in Json Format
        public String getLoginRequestBody(String email, String password){
            return new Gson().toJson(
                    LoginRequestDto.builder()
                    .email(email)
                    .password(password)
                    .build()
            );
        }
    //Get Verify Otp Request Body Information in Json Format
        public  String getVerifyOtpRequestBody(String otp, String email){
        return new Gson().toJson(
                VerifyOtpRequestDto.builder()
                .otp(otp)
                .email(email)
                .build()
        );
        }
    }

