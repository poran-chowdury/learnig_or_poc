package service;

import enums.AttachmentName;
import enums.StatusCode;
import httprequests.EndPoints;
import httprequests.Headers;
import io.restassured.response.Response;
import org.junit.Assert;

public class LoginService extends BaseService{

    protected Response response;

    //Customer Login
    public void customerLogin(String email, String password){
        System.out.println("Customer Login");
        response= requests.postRequest(
                EndPoints.customerLogin,
                bodyBuilder.getLoginRequestBody(email, password)
        );
        Assert.assertTrue(response.getStatusCode()== StatusCode.STATUS_OK.getStatusCode()||response.getStatusCode()==
                StatusCode.UNAUTHORIZED.getStatusCode());
        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }

    //Verify login from the response
    public void verifyLoginResponse(String statusCode){
        System.out.println("Verify the login response");
        response.then()
                .assertThat().statusCode(Integer.parseInt(statusCode));
        loginDetails.setLoginResponse(response);
        Headers.addAHeader("Authorization", loginDetails.getAuthToken());
    }

    //Admin login
    public void adminLogin(String email, String password){
        System.out.println("Admin Login");
        response=requests.postRequest(
                EndPoints.customerLogin,
                bodyBuilder.getLoginRequestBody(email, password)
        );
        Assert.assertTrue(response.getStatusCode()==StatusCode.STATUS_OK.getStatusCode()
        || response.getStatusCode()==StatusCode.UNAUTHORIZED.getStatusCode());

        validateAndAttachResponse(AttachmentName.LOGIN, response);
    }
}
