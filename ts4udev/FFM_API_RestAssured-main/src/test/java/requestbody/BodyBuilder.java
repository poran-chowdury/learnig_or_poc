package requestbody;

import com.google.gson.Gson;
import payload.LoginPayload;
import payload.RegisterCompanyManagerPayload;
import payload.RegisterCompanyPayload;
import payload.RegisterFMMPayload;

public class BodyBuilder {

    public String getRegisterFFMRequestBody(String firstName, String lastName, String email, String password, String username){
        return new Gson().toJson(RegisterFMMPayload
                .builder()
                .firstname(firstName).lastname(lastName)
                .email(email).username(username).password(password).build());
    }

    public String getCreateCompanyRequestBody(String name, String address, String tin){
        return new Gson().toJson(RegisterCompanyPayload
                .builder()
                .name(name).address(address)
                .tin(tin).build());
    }

    public String getCreateCompanyManagerRequestBody(String firstName, String lastName, String email, String password, String username, String company_id, String memberType){
        return new Gson().toJson(RegisterCompanyManagerPayload
                .builder()
                .firstname(firstName).lastname(lastName)
                .email(email).username(username).password(password)
                .company_id(company_id).member_type(Integer.parseInt(memberType)).build());
    }

    public String getLoginBody(String email, String password){
        return new Gson().toJson(LoginPayload
                .builder()
                .email(email).password(password).build());
    }
}
