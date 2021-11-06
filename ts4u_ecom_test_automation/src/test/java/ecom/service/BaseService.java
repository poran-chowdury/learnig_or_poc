package ecom.service;

import com.google.gson.Gson;
import ecom.dto.response.CreatedDataResponse;
import ecom.enums.AttachmentName;
import ecom.httprequests.Requests;
import ecom.providers.LoginDetails;
import ecom.requestBody.BodyBuilder;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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
