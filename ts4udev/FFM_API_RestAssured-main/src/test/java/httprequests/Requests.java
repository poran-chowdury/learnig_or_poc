package httprequests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

/***
 * This class will contain all methods that could be used for any
 * CRUD(POST, PUT, GET, DELETE) operations
 */
public class Requests {

    private static final Headers headers = new Headers();
    public StringWriter requestWriter;
    private final static RequestSpecification requestSpecification = RestAssured.given();

    private PrintStream getRequestLogger() {
        requestWriter = new StringWriter();
        return new PrintStream(new WriterOutputStream(requestWriter), true);
    }

    /***
     * method to create a new resource using POST method
     * @param endPoint - api endpoint url
     * @param body - inuput json payload as string
     * @return
     */
    public Response postRequest(String endPoint, String body){
        return given().log().all()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(Headers.getHeaders())
                .body(body)
                .post(endPoint);
    }

    /***
     * method to create a update an existing resource using PUT method
     * @param endPoint - api endpoint url
     * @param body - inuput json payload as string
     * @return
     */
    public Response putRequest(String endPoint, String body){
        return given().log().all()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(Headers.getHeaders())
                .body(body)
                .put(endPoint);
    }

    /***
     * method to retrieve an existing resource using GET method
     * @param endPoint - api endpoint url
     * @return
     */
    public Response getRequest(String endPoint) {
        return given().log().all()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(Headers.getHeaders())
                .get(endPoint);
    }

    /***
     * method to delete an existing resource using DELETE method
     * @param endPoint - api endpoint url
     * @return
     */
    public Response deleteRequest(String endPoint){
        return given().log().all()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(Headers.getHeaders())
                .body("test")
                .delete(endPoint);
    }
}
