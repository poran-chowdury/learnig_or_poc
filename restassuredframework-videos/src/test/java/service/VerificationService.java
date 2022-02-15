package service;

import dto.VerifyOtpResponseDto;
import enums.AttachmentName;
import enums.StatusCode;
import httprequests.EndPoints;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

public class VerificationService extends BaseService{
    protected Response response;

    public void verifyOtp(String otp, String email){
        System.out.println("Verify OTP");
        response=requests.postRequest(
                EndPoints.verifyOtp,
                bodyBuilder.getVerifyOtpRequestBody(otp,email));
        Assert.assertTrue(response.getStatusCode()== StatusCode.STATUS_OK.getStatusCode() || response.getStatusCode()
        == StatusCode.UNAUTHORIZED.getStatusCode());
        validateAndAttachResponse(AttachmentName.VERIFY_OTP, response);

    }

    //Verify OTP from the response
    public  boolean verifyOtpResponse(String statusCode, VerifyOtpResponseDto responseDto){
        System.out.println("Verify OTP from the response");
        if(response.getStatusCode()==StatusCode.STATUS_OK.getStatusCode()){
            response.then()
                    .assertThat()
                    .statusCode(Integer.parseInt(statusCode))
                    .body("success", CoreMatchers.equalTo(responseDto.getSuccess()));
        return true;
        }
    return false;
    }

    //Check OTP is expired or not
    public void checkOtpExpired(String statusCode, VerifyOtpResponseDto verifyOtpResponseDto){
        System.out.println("Check OTP is expired or not ");
        response.then()
                .assertThat()
                .statusCode(Integer.parseInt(statusCode))
                .body("message",CoreMatchers.equalTo(verifyOtpResponseDto.getMessage()));
    }


}
