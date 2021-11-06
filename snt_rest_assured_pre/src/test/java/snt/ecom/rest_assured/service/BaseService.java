package snt.ecom.rest_assured.service;

import com.google.gson.Gson;
import snt.ecom.rest_assured.dto.response.CreatedDataResponse;
import snt.ecom.rest_assured.enums.AttachmentName;
import snt.ecom.rest_assured.httprequests.Requests;
import snt.ecom.rest_assured.providers.LoginDetails;
import snt.ecom.rest_assured.requestBody.BodyBuilder;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 04:49
 */

@Getter
@Setter
public class BaseService {

    protected static LoginDetails loginDetails = new LoginDetails();
    protected Requests requests = new Requests();
    protected BodyBuilder bodyBuilder = new BodyBuilder();
    protected static CreatedDataResponse createdDataResponse;
    protected Gson gson = new Gson();

    protected String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
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
