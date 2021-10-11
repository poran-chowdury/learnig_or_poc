package ecom.service.buyer;

import ecom.dto.buyer.auth.response.VerifyOtpResponseDto;
import ecom.enums.AttachmentName;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.service.BaseService;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 04:48
 */

public class VerificationService extends BaseService {

    protected Response response;

    public void verifyOtp(String otp, String email) {

        System.out.println("verifyOtp");

        response = requests.postRequest(
            EndPoints.verifyOtp,
            bodyBuilder.getVerifyOtpRequestBody(otp, email)
        );

        Assert.assertTrue(
            response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode()
                || response.getStatusCode() == StatusCode.UNAUTHORIZED.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.VERIFY_OTP, response);
    }

    public Boolean verifyOtpResponse(String statusCode, VerifyOtpResponseDto responseDto) {

        System.out.println("verifyOtpResponse");

        if (response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode()) {
            response
                .then()
                .assertThat()
                .statusCode(Integer.parseInt(statusCode))
                .body("success", equalTo(responseDto.getSuccess()));

            return true;
        }

        return false;

    }

    public void checkOtpExpired(String statusCode, VerifyOtpResponseDto verifyOtpResponseDto) {

        System.out.println("checkOtpExpired");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode))
            .body("message", equalTo(verifyOtpResponseDto.getMessage()));

    }

}
