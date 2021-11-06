package providers;

import dto.login.LoginResponse;
import io.restassured.response.Response;

/***
 * return nodes from json response body of login api
 */
public class LoginDetails {

    LoginResponse loginResponse;

    public void setLoginResponse(Response response){
        loginResponse = response.getBody().as(LoginResponse.class);
    }

    public String getAuthToken(){
        return loginResponse.getAuth_token();
    }
}
