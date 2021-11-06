package snt.ecom.rest_assured.providers;

import snt.ecom.rest_assured.dto.response.LoginResponseDto;
import io.restassured.response.Response;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 23:54
 */

public class LoginDetails {

    LoginResponseDto loginResponse;

    public void setLoginResponse(Response response) {
        loginResponse = response.getBody().as(LoginResponseDto.class);
    }

    public String getAuthToken() {
        return loginResponse.getToken();
    }

}
