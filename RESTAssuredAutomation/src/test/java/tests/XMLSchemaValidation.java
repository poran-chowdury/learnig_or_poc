package tests;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author TOWFIQUL ISLAM
 * @since 19/07/2021 03:15
 */

public class XMLSchemaValidation {

    @Test
    public void schemaValidation() throws IOException {

        File file = new File("./SoapRequest/Add.xml");

        if (file.exists())
            System.out.println("  >>  File Exists");

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
            body("//*:AddResult.text()", equalTo("5")).
            and().
            assertThat().body(matchesXsdInClasspath("Calculator.xsd"));


    }

}
