package steps;

import enums.AttachmentName;
import enums.StatusCode;
import httprequests.EndPoints;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 19:52
 */

public class RegistrationSteps extends BaseSteps {

    protected static String ffm_email;
    protected Response response;

    public void registerFFMMember(String firstName, String lastName, String email, String password, String username) {
        String[] email_split = email.split("@");
        email = email_split[0] + randomString() + "@" + email_split[1];
        username += randomString();
        ffm_email = email;
        response = requests.postRequest(
            EndPoints.registerFFMEndpoint(),
            bodyBuilder.getRegisterFFMRequestBody(firstName, lastName, email, password, username)
        );
        Assert.assertTrue(
            response.getStatusCode() == StatusCode.STATUS_CREATED.getStatusCode()
                || response.getStatusCode() == StatusCode.ALREADY_EXISTS.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.CREATE_MANAGER, response);
    }

    public void verifyUserIsRegistered(String statusCode, String message) {
        response.then().assertThat().statusCode(Integer.parseInt(statusCode));
        response.then().assertThat().body(containsString(message));
    }

}
