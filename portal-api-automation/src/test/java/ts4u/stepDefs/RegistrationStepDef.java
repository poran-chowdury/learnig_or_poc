package ts4u.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ts4u.dto.buyer.auth.response.RegistrationResponseDto;
import ts4u.services.RegistrationService;

public class RegistrationStepDef {

    RegistrationService registrationService = new RegistrationService();

    @Given("Register a candidate with {string} {string} {string} {string} {string}")
    public void register_a_candidate_with(String firstname, String lastname, String email, String phone, String password) {
        registrationService.registerCustomer(firstname, lastname, email, phone, password);
    }

    @Then("Check the customer is created with status code {string} and isOtpSend {string} with this {string} or status code {string} and message {string}")
    public void check_the_customer_is_created_with_status_code_and_isOtpSend_with_this_or_status_code_and_message(
            String statusOk,
            String isOtpSend,
            String email,
            String statusBadRequest,
            String message) {

        Boolean isStatusOk = registrationService.verifyCustomerRegistrationResponse(
                statusOk,
                RegistrationResponseDto.builder()
                        .isOtpSend(isOtpSend.equals("true"))
                        .email(email)
                        .build()
        );
    }

    @Then("Make otp isVerified {string} for email {string}")
    public void make_otp_isVerified_for_email(String isVerified, String email) {
        System.out.println("3");
    }
}
