package steps;

import enums.AttachmentName;
import enums.StatusCode;
import httprequests.Requests;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.Assert;
import providers.CompanyDetails;
import providers.LoginDetails;
import requestbody.BodyBuilder;

import java.util.Random;

public class BaseSteps {

    protected Requests requests = new Requests();
    protected BodyBuilder bodyBuilder = new BodyBuilder();
    protected static CompanyDetails companyDetails = new CompanyDetails();
    protected static LoginDetails loginDetails = new LoginDetails();
    protected Response response;
    public static String ffm_email;
    protected static String ffm_password;
    protected static String ffm_username;
    public static String company_name;
    public static String manager_username;
    public static String manager_email;

    protected void validateAndAttachResponse(StatusCode expectedStatusCode, AttachmentName attachmentName, Response response){
        System.out.println(response.asString());
        Allure.addAttachment(attachmentName.getAttachmentName() + "Request",  requests.requestWriter.toString());
        Allure.addAttachment(attachmentName.getAttachmentName() + "Response", "Status code: " + response.statusCode()
                + System.lineSeparator() + "Response:" + System.lineSeparator() + response.asString());
        Assert.assertEquals("Status code not matched", expectedStatusCode.getStatusCode(), response.getStatusCode());
    }

    protected void validateAndAttachResponse(AttachmentName attachmentName, Response response){
        System.out.println(response.asString());
        Allure.addAttachment(attachmentName.getAttachmentName() + "Request",  requests.requestWriter.toString());
        Allure.addAttachment(attachmentName.getAttachmentName() + "Response", "Status code: " + response.statusCode()
                + System.lineSeparator() + "Response:" + System.lineSeparator() + response.asString());
    }

    protected String randomString(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
