package stepDefs;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.RegistrationResponseDto;
import enums.MongoDBCollection;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.codehaus.groovy.util.StringUtil;
import service.DBService;
import service.RegistrationService;

import java.util.HashMap;

public class BuyerRegistrationStepDef {
    RegistrationService registrationService= new RegistrationService();

    @Given("Register a buyer with {string} {string} {string}")
    public void register_a_buyer_with(String name, String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        registrationService.registerCustomer(name,email,password);

    }

    @When("Check the buyer is created with status code {string} and OTP send {string} with this {string}")
    public void check_the_buyer_is_created_with_status_code_and_OTP_send_with_this(String statusOk, String isOtpSend, String email, String statusBadRequest, String message) {
        // Write code here that turns the phrase above into concrete actions
        Boolean isStatusOk= registrationService.verifyCustomerRegistrationResponse(statusOk,
                RegistrationResponseDto.builder()
        .isOtpSend(isOtpSend.equals("true"))
        .email(email)
        .build());

        if (!isStatusOk){
            checkBuyerExist(statusBadRequest, message);
        }

    }

    @Then("Make otp is verified {string} for {string}")
    public void make_otp_is_verified_for(String otp, String email) {
        // Write code here that turns the phrase above into concrete actions
        DBService.isDBDataExist(MongoDBCollection.USERS, Filters.eq("email", email));
        HashMap<Object, Object> updateIsOtpVerified= new HashMap<>();
        updateIsOtpVerified.put("token", StringUtils.EMPTY);
        updateIsOtpVerified.put("isVerfied", otp);
        Bson updateDocument= Updates.set("otp", updateIsOtpVerified);
        UpdateOptions options= new UpdateOptions().upsert(true);
        DBService.updateDBData(updateDocument, options);

    }

    private void checkBuyerExist(String statusCode, String message){
        registrationService.customerExist(
                statusCode,
                RegistrationResponseDto.builder().message(message)
                .build()
        );
    }
}
