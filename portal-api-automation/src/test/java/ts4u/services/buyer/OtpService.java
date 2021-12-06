package ts4u.services.buyer;

import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import ts4u.enums.AttachmentName;
import ts4u.enums.StatusCode;
import ts4u.httprequests.EndPoints;
import ts4u.httprequests.Headers;
import ts4u.services.BaseService;

public class OtpService extends BaseService {

    protected Response response;

    public void ResendOtp(String email) {

        System.out.println("ResendOtp");

        response = requests.postRequest(
                EndPoints.resendOtp,
                bodyBuilder.getResendOtpRequestBody(email)
        );

        System.out.println(response.getBody().asString());

        Assert.assertTrue(
                response.getStatusCode() == StatusCode.STATUS_OK.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.RESEND_OTP, response);
    }
}
