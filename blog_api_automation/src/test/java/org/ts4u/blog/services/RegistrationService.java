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
public class RegistrationService extends BaseService {

    protected Response response;

    /**
     * @param first_name
     * @param last_name
     * @param email
     * @param password
     */
    public void registerUser(String first_name, String last_name, String email, String password) {

        System.out.println("registerUser");

        email = randomString() + email;

        response = requests.postRequest(
                EndPoints.registration,
                bodyBuilder.getRegistrationRequestBody(first_name, last_name, email, password)
        );

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.CREATE_USER, response);
    }

    /**
     * @param statusCode
     * @param isOtpSend
     */
    public void verifyUserRegistrationResponse(Integer statusCode, Boolean isOtpSend) {

        System.out.println("verifyUserRegistration");

        response
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("isOtpSend", equalTo(isOtpSend));


    }

    /**
     * @param email
     */
    public void resendOtp(String email) {
        System.out.println("resendOtp");

        response = requests.postRequest(
                EndPoints.resendOtp,
                bodyBuilder.getResendOtpBody(email)
        );

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.RESEND_OTP, response);
    }

    /**
     * @param statusCode
     * @param isOtpSend
     */
    public void verifyResendOtpResponse(Integer statusCode, Boolean isOtpSend) {
        System.out.println("verifyResendOtpResponse");

        response
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("isOtpSend", equalTo(isOtpSend));
    }
}
