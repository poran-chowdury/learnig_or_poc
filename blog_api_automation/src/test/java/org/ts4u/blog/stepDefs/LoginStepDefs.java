package org.ts4u.blog.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.ts4u.blog.services.LoginService;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:37 PM
 */

public class LoginStepDefs {

    LoginService loginService = new LoginService();

    /**
     * @param email
     * @param password
     */
    @Given("Login with user {string} and {string}")
    public void userLoginRequest(String email, String password) {
        loginService.userLogin(email, password);
    }

    /**
     * @param statusCode
     */
    @Then("Check login response for status code {string}")
    public void loginResponse(String statusCode) {
        loginService.verifyLoginResponse(Integer.parseInt(statusCode));
    }

    /**
     *
     */
    @Given("Call User Profile API and Get Profile JSON")
    public void userProfileJson() {
        loginService.getUserProfile();
    }

    /**
     * @param statusCode
     * @param success
     */
    @Then("Check response for status code {string} and success {string}")
    public void checkResponseForStatusCodeAndSuccess(String statusCode, String success) {
        loginService.verifyUserProfileResponse(Integer.parseInt(statusCode), Boolean.parseBoolean(success));
    }
}
