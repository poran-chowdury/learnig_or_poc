package org.ts4u.blog.services;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.ts4u.blog.enums.AttachmentName;
import org.ts4u.blog.httprequests.Requests;
import org.ts4u.blog.providers.LoginDetails;
import org.ts4u.blog.requestBody.BodyBuilder;

import java.util.Random;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */
@Getter
@Setter
public class BaseService {

    protected static LoginDetails loginDetails = new LoginDetails();
    protected Requests requests = new Requests();
    protected BodyBuilder bodyBuilder = new BodyBuilder();
    protected Gson gson = new Gson();

    /**
     * This method generates random string
     * @return String
     */
    protected String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * This method validate the Response and write it in allure report
     * @param attachmentName
     * @param response
     */
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
}
