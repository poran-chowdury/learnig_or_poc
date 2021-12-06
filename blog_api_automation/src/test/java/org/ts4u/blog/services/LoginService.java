package org.ts4u.blog.services;

import io.restassured.response.Response;
import org.junit.Assert;
import org.ts4u.blog.enums.AttachmentName;
import org.ts4u.blog.enums.StatusCode;
import org.ts4u.blog.httprequests.EndPoints;

import static org.hamcrest.CoreMatchers.equalTo;


/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */

public class LoginService extends BaseService {

    protected Response response;

    /**
     * This method calls Login API
     * @param email
     * @param password
     * @method POST
     */
    public void userLogin(String email, String password) {

        System.out.println("userLogin");

        response = requests.postRequest(
                EndPoints.userLogin,
                bodyBuilder.getLoginRequestBody(email, password)
        );

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        loginDetails.setLoginResponse(response);

        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }

    /**
     * This method verifies Login Response
     * @param statusCode
     */
    public void verifyLoginResponse(Integer statusCode) {

        System.out.println("verifyLoginResponse");

        response
                .then()
                .assertThat()
                .statusCode(statusCode);


    }

    /**
     * This method calls User Profile API
     * @method GET
     */
    public void getUserProfile() {
        System.out.println("getUserProfile");

        response = requests.getRequest(EndPoints.userProfile, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }

    /**
     * This method verifies User Profile Response
     * @param statusCode
     * @param success
     */
    public void verifyUserProfileResponse(Integer statusCode, Boolean success) {
        System.out.println("verifyUserProfileResponse");

        response
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("success", equalTo(success));
    }
}
