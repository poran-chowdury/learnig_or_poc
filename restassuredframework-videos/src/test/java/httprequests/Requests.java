package httprequests;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;
import static io.restassured.RestAssured.given;

public class Requests {
    public StringWriter requestWriter;

    //Request info
    public Response postRequest(String endPoint, String body){
        return given().log().all()
                .filter(new RequestLoggingFilter(getRequestLogger()))
                .headers(Headers.getHeaders())
                .body(body)
                .post(endPoint);
    }
    //Print request logs
    private PrintStream getRequestLogger(){
        requestWriter= new StringWriter();
        return new PrintStream(new WriterOutputStream(requestWriter), true);
    }
}
