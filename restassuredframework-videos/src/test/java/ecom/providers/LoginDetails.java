package ecom.providers;

import ecom.dto.LoginResponseDto;
import io.restassured.response.Response;

public class LoginDetails {

    LoginResponseDto loginResponse;

    //Set Login Response
    public void setLoginResponse(Response response){
        loginResponse=response.getBody().as(LoginResponseDto.class);
    }

    //Get Auth Token
    public String getAuthToken(){
        return loginResponse.getToken();
    }
}
