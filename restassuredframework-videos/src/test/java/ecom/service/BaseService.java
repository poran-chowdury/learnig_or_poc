package ecom.service;

import com.google.gson.Gson;
import ecom.enums.AttachmentName;
import ecom.httprequests.Requests;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import ecom.providers.LoginDetails;
import ecom.requestBody.BodyBuilder;

import java.util.Random;

public class BaseService {

    protected static LoginDetails loginDetails= new LoginDetails();
    protected Requests requests= new Requests();
    protected BodyBuilder bodyBuilder= new BodyBuilder();
    protected Gson gson= new Gson();

    //Generate Random String
    protected String randomString(){
        int lefLimit=97; //Letter 'a'
        int rightLimit= 122; //Letter 'z'
        int targetStringLength= 10;

        Random random= new Random();
        String generatedString= random.ints(lefLimit, rightLimit +1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    //Attach report of the responses
    protected void validateAndAttachResponse(AttachmentName attachmentName, Response response) {
        Allure.addAttachment(attachmentName.getAttachmentName() + "Request", requests.requestWriter.toString());
        Allure.addAttachment(
                attachmentName.getAttachmentName()
                        + "Response", "Status Code: "
                        + response.statusCode()
                        + System.lineSeparator()
                        + "Response: "
                        + System.lineSeparator()
                        + response.asString()
        );
    }

    //Attach report of the database validation
    protected  void validateAndAttachDBTestResult(AttachmentName attachmentName, String request, String response){
        Allure.addAttachment(attachmentName.getAttachmentName()+"Request", request);
        Allure.addAttachment(
                attachmentName.getAttachmentName()
                +"Response", "Status Code: "
                +System.lineSeparator()
                +"Response: "
                +System.lineSeparator()
                +response
        );
    }

    }




