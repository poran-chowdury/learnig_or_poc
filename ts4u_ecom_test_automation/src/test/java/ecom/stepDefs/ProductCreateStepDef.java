package ecom.stepDefs;

import cucumber.api.java.en.Then;
import ecom.service.admin.ProductService;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 01:47
 */

public class ProductCreateStepDef {

    ProductService productService = new ProductService();

    @Then("Create Product JSON payload")
    public void createProductJSONPayload() {
        productService.prepareProductRequestDto();
    }

    @Then("request with created JSON payload")
    public void requestWithCreatedJSONPayload() {
        productService.requestProductCreateApi();
    }

    @Then("check for product create response with status code {string}")
    public void checkForProductCreateResponseWithStatusCode(String statusOk) {
        productService.verifyCreateProductResponse(statusOk);
    }

    @Then("Verify created new product in DB")
    public void verifyCreatedNewProductInDB() {
        productService.verifyCreatedProductInDB();
    }
}
