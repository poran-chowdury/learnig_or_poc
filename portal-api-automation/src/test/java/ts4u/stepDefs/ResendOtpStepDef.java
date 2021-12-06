package ts4u.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ts4u.services.buyer.OtpService;

public class ResendOtpStepDef {

    OtpService otpService = new OtpService();

    @Given("Resend Otp with Registered {string}")
    public void resend_Otp_with_Registered(String email) {
        otpService.ResendOtp(email);
    }

    @Then("Check Resend Otp for status code {string} and success is {string}")
    public void check_Resend_Otp_for_status_code_and_success_is(String statusCode, String success) {
        // otpService.verifyResendOtpResponse(statusCode, Boolean.parseBoolean(success));
    }
}