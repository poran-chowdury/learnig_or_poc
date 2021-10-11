package ecom.service;

import ecom.dto.buyer.auth.response.RegistrationResponseDto;
import ecom.enums.AttachmentName;
import ecom.enums.MongoDBCollection;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.service.BaseService;
import ecom.service.DBService;
import io.restassured.response.Response;
import org.bson.Document;
import org.junit.Assert;

import java.util.Date;
import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 04:45
 */

public class RegistrationService extends BaseService {

    protected Response response;

    public void registerCustomer(String name, String email, String password) {

        System.out.println("registerCustomer");

        response = requests.postRequest(
            EndPoints.registration,
            bodyBuilder.getRegistrationRequestBody(name, email, password)
        );

        System.out.println("Response :: " + response.prettyPrint());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertTrue(
            response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode() || response.getStatusCode() == StatusCode.BAD_REQUEST.getStatusCode()
        );

        validateAndAttachResponse(AttachmentName.CREATE_CUSTOMER, response);
    }

    public Boolean verifyCustomerRegistrationResponse(String statusCode, RegistrationResponseDto responseDto) {

        System.out.println("verifyCustomerRegistration");

        if (response.statusCode() == StatusCode.STATUS_OK.getStatusCode()) {
            response
                .then()
                .assertThat()
                .statusCode(Integer.parseInt(statusCode))
                .body("isOtpSend", equalTo(responseDto.getIsOtpSend()))
                .body("email", equalTo(responseDto.getEmail()));

            return true;
        }

        return false;
    }

    public void customerExist(String statusCode, RegistrationResponseDto responseDto) {

        System.out.println("customerExist");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode))
            .body("message", equalTo(responseDto.getMessage()));
    }

    public void checkForUserData(String email) {

        System.out.println("checkForUserData");

        boolean isDataExist = DBService.isDBDataExist(MongoDBCollection.USERS, eq("email", email));

        Assert.assertTrue(isDataExist);

        validateAndAttachDBTestResult(AttachmentName.CREATE_CUSTOMER, email, String.valueOf(isDataExist));

    }

    public boolean checkForAdminData(String email) {

        System.out.println("checkForAdminData");

        boolean isDataExist = DBService.isDBDataExist(MongoDBCollection.USERS, eq("email", email));

        validateAndAttachDBTestResult(AttachmentName.CREATE_CUSTOMER, email, String.valueOf(isDataExist));

        return isDataExist;

    }

    public void createAdmin(String name, String email, String hashPassword) {
        System.out.println("createAdmin");

        HashMap<String, Object> otp = new HashMap<>();

        otp.put("token", "");
        otp.put("isVerified", true);

        Document document = new Document();

        document.append("otp", otp);
        document.append("mobile", "");
        document.append("role", "admin");
        document.append("profilePicture", "");
        document.append("isActive", true);
        document.append("isActive", true);
        document.append("name", name);
        document.append("email", email);
        document.append("hash_password", hashPassword);
        document.append("createdAt", new Date());
        document.append("updatedAt", new Date());
        document.append("__v", 0);

        DBService.createDBData(MongoDBCollection.USERS, document);

    }

}
