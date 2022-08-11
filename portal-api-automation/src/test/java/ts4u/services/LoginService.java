package ts4u.services;

import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import ts4u.enums.AttachmentName;
import ts4u.enums.StatusCode;
import ts4u.httprequests.EndPoints;
import ts4u.httprequests.Headers;

public class LoginService extends BaseService {

    protected Response response;

    public void customerLogin(String email, String password) {

        System.out.println("customerLogin");

        response = requests.postRequest(
            EndPoints.customerLogin,
            bodyBuilder.getLoginRequestBody(email, password)
        );

        System.out.println(response.getBody().asString());

        Assert.assertTrue(
            response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }

    public void verifyLoginResponse(String statusCode, Boolean success) {

        System.out.println("verifyLoginResponse");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode))
            .body("success", CoreMatchers.equalTo(success));

        loginDetails.setLoginResponse(response);
        Headers.addAHeader("Authorization", loginDetails.getAuthToken());
    }
}
