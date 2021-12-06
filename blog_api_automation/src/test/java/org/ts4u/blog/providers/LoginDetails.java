package org.ts4u.blog.providers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.ts4u.blog.dto.response.LoginResponseDto;
import org.ts4u.blog.httprequests.Headers;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */

public class LoginDetails {

    LoginResponseDto loginResponse;

    /**
     * Extract the token value
     * set the token in LoginResponse
     * @param Response response
     */
    public void setLoginResponse(Response response) {
        JsonPath responseJson = new JsonPath(response.asString());
        String token = responseJson.getString("token");
        loginResponse = LoginResponseDto.builder().token(token).build();
        setAuthenticationHeader();
    }

    /**
     * Return auth token
     *
     * @return string
     */
    public String getAuthToken() {
        return loginResponse.getToken();
    }

    /**
     * set Authorization Token
     */
    private void setAuthenticationHeader() {
        Headers.addAHeader("Authorization", "Bearer " + getAuthToken());
    }

}
