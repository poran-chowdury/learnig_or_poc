package steps;

import enums.AttachmentName;
import httprequests.Requests;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import requestBody.BodyBuilder;

import java.util.Random;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 19:53
 */

public class BaseSteps {

    protected Requests requests = new Requests();
    protected BodyBuilder bodyBuilder = new BodyBuilder();


    protected String randomString() {
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

    protected void validateAndAttachResponse(AttachmentName attachmentName, Response response) {
        System.out.println(response.asString());
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
}
