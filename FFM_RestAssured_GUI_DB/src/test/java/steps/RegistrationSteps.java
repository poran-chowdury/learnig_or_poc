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
        Assert.assertTrue("Status code not matched", response.getStatusCode()==StatusCode.STATUS_CREATED.getStatusCode() || response.getStatusCode()==StatusCode.ALREADY_EXISTS.getStatusCode());
        validateAndAttachResponse(AttachmentName.CREATE_MANAGER, response);
    }

    public void registerCompany(String name, String address, String tin){
        name+= randomString();
        tin+= randomString();
        company_name = name;
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
