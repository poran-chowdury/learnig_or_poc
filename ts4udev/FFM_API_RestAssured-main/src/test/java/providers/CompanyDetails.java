package providers;

import dto.company.CreateCompanyResponse;
import io.restassured.response.Response;

public class CompanyDetails {

    CreateCompanyResponse createCompanyResponse;

    public void setCompanyResponse(Response response){
        createCompanyResponse = response.getBody().as(CreateCompanyResponse.class);
    }

    public String getCompanyId(){
        return createCompanyResponse.getData().getCompany_id();
    }
}
