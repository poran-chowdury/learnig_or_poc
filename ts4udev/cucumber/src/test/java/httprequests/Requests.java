package httprequests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;
import requestBody.BodyBuilder;

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
     * @param body     - inuput json payload as string
     * @return
     */
    public Response postRequest(String endPoint, String body) {
        return given().log().all()
            .filter(new RequestLoggingFilter(getRequestLogger()))
            .headers(Headers.getHeaders())
            .body(body)
            .post(endPoint);
    }

    private PrintStream getRequestLogger() {
        requestWriter = new StringWriter();
        return new PrintStream(new WriterOutputStream(requestWriter), true);
    }
}
