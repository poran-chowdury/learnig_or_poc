package snt.ecom.rest_assured.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import snt.ecom.rest_assured.dto.response.RegistrationResponseDto;
import snt.ecom.rest_assured.service.DBService;
import snt.ecom.rest_assured.service.RegistrationService;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 04:40
 */

public class RegistrationStepDef {

    RegistrationService registrationService = new RegistrationService();

    @Given("Register a customer with {string} {string} {string}")
    public void registerCustomer(String name, String email, String password) {
        registrationService.registerCustomer(name, email, password);
    }

    @Then("Check the customer is created with status code {string} and isOtpSend {string} with this {string} or status code {string} and message {string}")
    public void checkCustomerRegistrationResponse(String statusOk,
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

        if (!isStatusOk) {
            checkCustomerExist(statusBadRequest, message);
        }
    }

    @Given("Check with collection name {string}")
    public void checkForEmail(String email) {
        registrationService.checkForUserData(email);
    }

    @Given("Check with collection email {string} with Response {string} and I delete DB data")
    public void checkAdminEmail(String email, String response) {

        boolean isAdminExist = registrationService.checkForAdminData(email);

        if (isAdminExist == Boolean.parseBoolean(response)) {
            {
                DBService.deleteQuery();
            }
        }
    }

    @Then("create a admin user using {string} {string} and {string}")
    public void createAdminUser(String name, String email, String hashPassword) {
        registrationService.createAdmin(name, email, hashPassword);
    }

    private void checkCustomerExist(String statusCode, String message) {
        registrationService.customerExist(
            statusCode,
            RegistrationResponseDto.builder()
                .message(message)
                .build()
        );
    }
}
