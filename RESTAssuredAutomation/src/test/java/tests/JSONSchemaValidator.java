package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


/**
 * @author TOWFIQUL ISLAM
 * @since 19/07/2021 03:15
 */

public class JSONSchemaValidator {

    @Test
    public void testGet() {

        baseURI = "https://reqres.in/api";

        given().
            get("/users?page=2").
            then().
            assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
            statusCode(200);

    }

}
