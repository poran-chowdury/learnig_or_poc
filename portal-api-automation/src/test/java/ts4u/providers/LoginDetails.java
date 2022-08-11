package ts4u.providers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import ts4u.response.LoginResponseDto;

public class LoginDetails {

    LoginResponseDto loginResponse;

    public void setLoginResponse(Response response) {

        String rbody = response.getBody().asString();
        JsonPath jp = new JsonPath(rbody);
        String token = jp.getString( "token" );

        System.out.println("token");
        System.out.println(token);

        loginResponse = LoginResponseDto.builder().token(token).build();
        // loginResponse = response.getBody().as(LoginResponseDto.class);
    }

    public String getAuthToken() {
        return loginResponse.getToken();
    }

    private String getToken(String tokenWithBearer) {
        return tokenWithBearer.split(" ")[1];
    }
}
