package ts4u.services;

import com.google.gson.Gson;
import ts4u.dto.response.CreatedDataResponse;
import ts4u.enums.AttachmentName;
import ts4u.httprequests.Requests;
import ts4u.providers.LoginDetails;
import ts4u.requestBody.BodyBuilder;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class BaseService {

    protected static LoginDetails loginDetails = new LoginDetails();
    protected Requests requests = new Requests();
    protected BodyBuilder bodyBuilder = new BodyBuilder();
    protected static CreatedDataResponse createdDataResponse;
    protected Gson gson = new Gson();

    protected String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random
            .ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    protected void validateAndAttachResponse(AttachmentName attachmentName, Response response) {

        Allure.addAttachment(attachmentName.getAttachmentName() + "Request", requests.requestWriter.toString());

        Allure.addAttachment(
            attachmentName.getAttachmentName()
                + "Response", "Status code: "
                + response.statusCode()
                + System.lineSeparator()
                + "Response:"
                + System.lineSeparator()
                + response.asString()
        );
    }

    protected void validateAndAttachDBTestResult(AttachmentName attachmentName, String request, String response) {

        Allure.addAttachment(attachmentName.getAttachmentName() + "Request", request);
        Allure.addAttachment(attachmentName.getAttachmentName() + "Request", request);

        Allure.addAttachment(
            attachmentName.getAttachmentName()
                + "Response", "Status code: "
                + System.lineSeparator()
                + "Response:"
                + System.lineSeparator()
                + response
        );
    }

}
