package org.ts4u.blog.httprequests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */

public class Requests {

    public StringWriter requestWriter;

    /**
     * method to create a new resource using POST method
     *
     * @param endPoint - api endpoint url
     * @param body     - input json payload as string
     */
    public Response postRequest(String endPoint, String body) {
        return given().log().all()
            .filter(new RequestLoggingFilter(getRequestLogger()))
            .headers(Headers.getHeaders())
            .body(body)
            .post(endPoint);
    }

    /**
     * method to create a new resource using GET method
     *
     * @param endPoint - api endpoint url
     * @param body     - input json payload as string
     */
    public Response getRequest(String endPoint, String body) {
        return given().log().all()
            .filter(new RequestLoggingFilter(getRequestLogger()))
            .headers(Headers.getHeaders())
            .body(body)
            .get(endPoint);
    }

    /**
     * It writes log
     * @return PrintStream
     */
    private PrintStream getRequestLogger() {
        requestWriter = new StringWriter();
        return new PrintStream(new WriterOutputStream(requestWriter), true);
    }
}
