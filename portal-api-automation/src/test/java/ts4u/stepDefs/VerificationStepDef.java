package ts4u.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ts4u.dto.buyer.auth.response.VerifyOtpResponseDto;
import ts4u.services.buyer.VerificationService;
import ts4u.utils.CustomIOUtils;

import java.io.IOException;

public class VerificationStepDef {

    VerificationService verificationService = new VerificationService();

    private String otp;

    @Given("Get the OTP that is given in email")
    public void get_the_OTP_that_is_given_in_email() throws IOException, InterruptedException {
        this.otp = CustomIOUtils.getOtp();
    }

    @Then("Call Verify OTP and {string}")
    public void call_Verify_OTP_and(String email) {
        verificationService.verifyOtp(this.otp, email);
    }

    @Then("Check the response with status code {string} and success {string} for verified OTP or status code {string} and message {string}")
    public void check_the_response_with_status_code_and_success_for_verified_OTP_or_status_code_and_message(
            String statusOk,
            String success,
            String statusUnauthorized,
            String message) {

        Boolean isStatusOk = verificationService.verifyOtpResponse(
                statusOk,
                VerifyOtpResponseDto.builder()
                        .success(success.equals("true"))
                        .build()
        );

        if (!isStatusOk) {
            verificationService.checkOtpExpired(
                    statusUnauthorized,
                    VerifyOtpResponseDto.builder()
                            .message(message)
                            .build()
            );
        }
    }
}