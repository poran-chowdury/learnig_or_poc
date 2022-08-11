package ts4u.services.buyer;

import io.restassured.response.Response;
import org.junit.Assert;
import ts4u.dto.buyer.auth.response.VerifyOtpResponseDto;
import ts4u.enums.AttachmentName;
import ts4u.enums.StatusCode;
import ts4u.httprequests.EndPoints;
import ts4u.services.BaseService;

import static org.hamcrest.CoreMatchers.equalTo;

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
