package ts4u.services;

import io.restassured.response.Response;
import org.junit.Assert;
import ts4u.dto.buyer.auth.response.RegistrationResponseDto;
import ts4u.enums.AttachmentName;
import ts4u.enums.StatusCode;
import ts4u.httprequests.EndPoints;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationService extends BaseService {

    protected Response response;

    public void registerCustomer(String firstname, String lastname, String email, String phone, String password) {

        System.out.println("registerCustomer");

        response = requests.postRequest(
            EndPoints.registration,
            bodyBuilder.getRegistrationRequestBody(firstname, lastname, email, phone, password)
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
}
