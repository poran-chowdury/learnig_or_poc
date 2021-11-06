package tests;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author TOWFIQUL ISLAM
 * @since 19/07/2021 03:15
 */

public class SoapXMLRequest {

    @Test
    public void validateSoapXML() throws IOException {

        File file = new File("./SoapRequest/Add.xml");

        boolean isFileExist = file.exists();

//        if (isFileExist)
//            System.out.println("  >>  File Exists");

        Assert.assertTrue(isFileExist);

        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

        baseURI = "http://www.dneonline.com";

        given().
            contentType("text/xml").
            accept(ContentType.XML).
            body(requestBody).
            when().
            post("/calculator.asmx").
            then().
            statusCode(200).log().all().
            and().
            body("//*:AddResult.text()", equalTo("5"));


    }

}

