package snt.ecom.rest_assured.stepDefs;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import snt.ecom.rest_assured.dto.response.VerifyOtpResponseDto;
import snt.ecom.rest_assured.enums.MongoDBCollection;
import snt.ecom.rest_assured.service.DBService;
import snt.ecom.rest_assured.service.VerificationService;

import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 21:07
 */

public class VerificationStepDef {

    VerificationService verificationService = new VerificationService();

    @Then("Check the response with status code {string} and success {string} for verified OTP or status code {string} and message {string}")
    public void checkVerificationResponse(String statusOk, String success, String statusUnauthorized, String message) {
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

    @Then("Make otp isVerified {string} for email {string}")
    public void updateOtp(String otp, String email) {
        System.out.println("updateOtp");

        DBService.isDBDataExist(MongoDBCollection.USERS, eq("email", email));

        HashMap<Object, Object> updateIsOtpVerified = new HashMap<>();
        updateIsOtpVerified.put("token", StringUtils.EMPTY);
        updateIsOtpVerified.put("isVerified", otp);

        Bson updateDocument = Updates.set("otp", updateIsOtpVerified);

        UpdateOptions options = new UpdateOptions().upsert(true);

        DBService.updateDBData(updateDocument, options);

    }
}
