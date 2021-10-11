package steps;

import enums.AttachmentName;
import enums.StatusCode;
import httprequests.EndPoints;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.containsString;

public class RegistrationSteps extends BaseSteps{

    public String getCompanyId(){
        return companyDetails.getCompanyId();
    }

    public void registerFFMMember(String firstName, String lastName, String email, String password, String username){
        String[] email_split = email.split("@");
        email = email_split[0] + randomString() + "@" + email_split[1];
        username+=randomString();
        ffm_email = email;
        response = requests.postRequest(EndPoints.registerFFMEndpoint(), bodyBuilder.getRegisterFFMRequestBody(firstName, lastName, email, password, username));
        Assert.assertTrue(response.getStatusCode()==StatusCode.STATUS_CREATED.getStatusCode() || response.getStatusCode()==StatusCode.ALREADY_EXISTS.getStatusCode());
        validateAndAttachResponse(AttachmentName.CREATE_MANAGER, response);
    }

    public void registerCompany(String name, String address, String tin){
        name+= randomString();
        tin+= randomString();
        response = requests.postRequest(EndPoints.registerCompanyEndpoint(), bodyBuilder.getCreateCompanyRequestBody(name, address, tin));
        response.then().assertThat().statusCode(StatusCode.STATUS_CREATED.getStatusCode());
        validateAndAttachResponse(StatusCode.STATUS_CREATED, AttachmentName.CREATE_COMPANY, response);
        companyDetails.setCompanyResponse(response);
    }

    public void registerCompanyManager(String firstName, String lastName, String email, String password, String username, String company_id, String memberType){
        String[] email_split = email.split("@");
        email = email_split[0] + randomString() + "@" + email_split[1];
        username+=randomString();
        manager_email=email;
        manager_username=username;
        response = requests.postRequest(EndPoints.registerCompanyManagerEndpoint(), bodyBuilder.getCreateCompanyManagerRequestBody(firstName, lastName, email, password, username, company_id, memberType));
        validateAndAttachResponse(StatusCode.STATUS_CREATED, AttachmentName.CREATE_MANAGER, response);
    }

   /* public void login(String email, String password){
        response = requests.postRequest(EndPoints.loginEndpoint(), bodyBuilder.getLoginBody(email, password));
        response.then().assertThat().statusCode(StatusCode.STATUS_OK.getStatusCode());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.LOGIN, response);
        loginDetails.setLoginResponse(response);
        Headers.addAHeader("Authorization","bearer" + " " + loginDetails.getAuthToken());
    }

    public void logout(){
        response = requests.getRequest(EndPoints.logoutEndpoint());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.LOGOUT, response);
    }

    public void authorizeUser(){
        response = requests.getRequest(EndPoints.authorizationEndpoint());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.AUTHORIZE_USER, response);
    }

    public void generateRefreshToken(){
        response = requests.getRequest(EndPoints.refreshTokenEndpoint());
        validateAndAttachResponse(StatusCode.STATUS_OK, AttachmentName.REFRESH_TOKEN, response);
    }

    public void verifyUserIsLoggedIn(String statusCode, String message){
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }*/

    public void verifyUserIsRegistered(String statusCode, String message){
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }

    public void verifyCompanyIsRegistered(String statusCode, String message){
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }

    public void verifyCompanyManagerIsRegistered(String statusCode, String message){
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }
}
