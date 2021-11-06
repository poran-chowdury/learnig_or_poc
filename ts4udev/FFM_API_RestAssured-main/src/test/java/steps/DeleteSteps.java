package steps;

import enums.AttachmentName;
import enums.StatusCode;
import httprequests.EndPoints;

import static org.hamcrest.Matchers.containsString;

public class DeleteSteps extends BaseSteps {

    public void deleteCompany(){
        response = requests.deleteRequest(EndPoints.deleteCompanyEndpoint(companyDetails.getCompanyId()));
        response.then().assertThat().statusCode(StatusCode.DELETED.getStatusCode());
        validateAndAttachResponse(StatusCode.DELETED, AttachmentName.DELETE_COMPANY, response);
    }

    public void deleteCompanyManager(){
        response = requests.deleteRequest(EndPoints.deleteCompanyManagerEndpoint("managerId"));
        response.then().assertThat().statusCode(StatusCode.DELETED.getStatusCode());
        validateAndAttachResponse(StatusCode.DELETED, AttachmentName.DELETE_MANAGER, response);
    }

    public void deleteFFMUser(String memberId){
        response = requests.deleteRequest(EndPoints.deleteMemberEndpoint(memberId));
        validateAndAttachResponse(StatusCode.DELETED, AttachmentName.DELETE_FFM_USER, response);
    }

    public void verifyUserIsDeleted(String statusCode, String message){
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }
}
