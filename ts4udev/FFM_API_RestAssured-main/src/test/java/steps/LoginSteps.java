package steps;

import enums.AttachmentName;
import enums.StatusCode;
import httprequests.EndPoints;
import httprequests.Headers;

import static org.hamcrest.CoreMatchers.containsString;

public class LoginSteps extends BaseSteps{

    public void login(String email, String password){
        response = requests.postRequest(EndPoints.loginEndpoint(), bodyBuilder.getLoginBody(ffm_email, password));
        response.then().assertThat().statusCode(StatusCode.STATUS_OK.getStatusCode());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.LOGIN, response);
        loginDetails.setLoginResponse(response);
        Headers.addAHeader("Authorization","bearer" + " " + loginDetails.getAuthToken());
    }

    public void loginManager(String email, String password){
        response = requests.postRequest(EndPoints.loginEndpoint(), bodyBuilder.getLoginBody(manager_email, password));
        response.then().assertThat().statusCode(StatusCode.STATUS_OK.getStatusCode());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.LOGIN, response);
        loginDetails.setLoginResponse(response);
        Headers.addAHeader("Authorization","bearer" + " " + loginDetails.getAuthToken());
    }

    public void logout(){
        response = requests.getRequest(EndPoints.logoutEndpoint());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.LOGOUT, response);
    }

    public void verifyUserIsLoggedIn(String statusCode, String message){
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }

    public void verifyUserIsLoggedOut(){
        response.then().assertThat().statusCode(StatusCode.STATUS_OK.getStatusCode());
    }

    public void authorizeUser(){
        response = requests.getRequest(EndPoints.authorizationEndpoint());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.AUTHORIZE_USER, response);
    }

    public void generateRefreshToken(){
        response = requests.getRequest(EndPoints.refreshTokenEndpoint());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.REFRESH_TOKEN, response);
    }
}


