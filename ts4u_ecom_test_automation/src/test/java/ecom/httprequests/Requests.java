package ecom.httprequests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:05
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

    private PrintStream getRequestLogger() {
        requestWriter = new StringWriter();
        return new PrintStream(new WriterOutputStream(requestWriter), true);
    }
}
