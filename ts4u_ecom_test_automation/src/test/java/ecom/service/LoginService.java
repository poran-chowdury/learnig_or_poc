package ecom.service;

import ecom.enums.AttachmentName;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.httprequests.Headers;
import io.restassured.response.Response;
import org.junit.Assert;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 04:48
 */

public class LoginService extends BaseService {

    protected Response response;

    public void customerLogin(String email, String password) {

        System.out.println("customerLogin");

        response = requests.postRequest(
            EndPoints.customerLogin,
            bodyBuilder.getLoginRequestBody(email, password)
        );

        Assert.assertTrue(
            response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode()
                || response.getStatusCode() == StatusCode.UNAUTHORIZED.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }

    public void verifyLoginResponse(String statusCode) {

        System.out.println("verifyLoginResponse");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode));

        loginDetails.setLoginResponse(response);
        Headers.addAHeader("Authorization", loginDetails.getAuthToken());

    }

    public void adminLogin(String email, String password) {

        System.out.println("adminLogin");

        response = requests.postRequest(
            EndPoints.customerLogin,
            bodyBuilder.getLoginRequestBody(email, password)
        );

        Assert.assertTrue(
            response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode()
                || response.getStatusCode() == StatusCode.UNAUTHORIZED.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }

}
