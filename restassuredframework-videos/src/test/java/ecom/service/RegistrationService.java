package ecom.service;

import ecom.dto.RegistrationResponseDto;
import ecom.enums.AttachmentName;
import ecom.enums.MongoDBCollection;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.providers.Credentials;
import io.restassured.response.Response;
import org.bson.Document;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import java.util.Date;
import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;


public class RegistrationService extends BaseService {
    protected Response response;

    //Register Customer
    public void registerCustomer(String name, String email, String password) {
        System.out.println("Register Customer");

        email = randomString() + email;
        Credentials.addCredential("email", email);
        response = requests.postRequest(
                EndPoints.registration,
                bodyBuilder.getRegistrationRequestBody(name, email, password)
        );
        Assert.assertTrue(response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode() || response.getStatusCode() ==
                StatusCode.BAD_REQUEST.getStatusCode());
        validateAndAttachResponse(AttachmentName.CREATE_CUSTOMER, response);
    }

    //Verify customer registration

    public Boolean verifyCustomerRegistrationResponse(String statusCode, RegistrationResponseDto responseDto) {
        System.out.println("Verify Customer Registration");
        if (response.statusCode() == StatusCode.STATUS_OK.getStatusCode()) {
            response.then()
                    .assertThat()
                    .statusCode(Integer.parseInt(statusCode))
                    .body("isOtpSend", CoreMatchers.equalTo(responseDto.getIsOtpSend()))
                    .body("email", CoreMatchers.equalTo(responseDto.getEmail()));
            return true;
        }
        return false;
    }

    //Verify the existing customer
    public void customerExist(String statusCode, RegistrationResponseDto responseDto) {
        System.out.println("This customer is already exist");
        response.then()
                .assertThat()
                .statusCode(Integer.parseInt(statusCode))
                .body("message", CoreMatchers.equalTo(responseDto.getMessage()));
    }

    //Verify the user's data from the database
    public void checkForUserData(String email) {
        System.out.println("Check for the user's data");
        boolean isDataExist = DBService.isDBDataExist(MongoDBCollection.USERS, eq("email", email));
        Assert.assertTrue(isDataExist);
        validateAndAttachDBTestResult(AttachmentName.CREATE_CUSTOMER, email, String.valueOf(isDataExist));
    }

    //Verify the admin's data from the database
    public boolean checkForAdminData(String email) {
        System.out.println("Check for the admin's data");
        boolean isDataExist = DBService.isDBDataExist(MongoDBCollection.USERS, eq("email", email));
        validateAndAttachDBTestResult(AttachmentName.CREATE_CUSTOMER, email, String.valueOf(isDataExist));
        return isDataExist;
    }

    //Create admin
    public void createAdmin(String name, String email, String hashPassword) {
        System.out.println("Create admin");
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
        document.append("email,", email);
        document.append("hash_password", hashPassword);
        document.append("createdAt", new Date());
        document.append("updatedAt", new Date());
        document.append("__v", 0);
        DBService.createDBData(MongoDBCollection.USERS, document);

    }


}
