package org.ts4u.blog.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.ts4u.blog.services.RegistrationService;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:37 PM
 */

public class RegistrationStepDefs {

    RegistrationService registrationService = new RegistrationService();

    /**
     * @param first_name
     * @param last_name
     * @param email
     * @param password
     */
    @Given("Register a user with {string} {string} {string} {string}")
    public void registerUser(String first_name, String last_name, String email, String password) {
        registrationService.registerUser(first_name, last_name, email, password);
    }

    /**
     * @param statusCode
     * @param isOtpSend
     */
    @Then("Check the user is created with status code {string} and isOtpSend {string}")
    public void checkUserRegistrationResponse(String statusCode, String isOtpSend) {
        registrationService.verifyUserRegistrationResponse(
                Integer.parseInt(statusCode),
                Boolean.parseBoolean(isOtpSend)
        );
    }

    /**
     * @param email
     */
    @Given("User {string}")
    public void resendOtp(String email) {
        registrationService.resendOtp(email);
    }

    /**
     * @param statusCode
     * @param isOtpSend
     */
    @Then("Check response for status code {string} and isOtpSend {string}")
    public void checkResponseForIsOtpSend(String statusCode, String isOtpSend) {
        registrationService.verifyResendOtpResponse(Integer.parseInt(statusCode), Boolean.parseBoolean(isOtpSend));
    }
}
