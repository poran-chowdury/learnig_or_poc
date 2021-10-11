package ecom.stepDefs;

import cucumber.api.java.en.Then;
import ecom.service.buyer.AddressService;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 03:03
 */

public class AddressStepDef {

    AddressService addressService = new AddressService();

    @Then("Prepare address request data")
    public void prepareAddressRequestData() {
        addressService.prepareAddressRequestDto();
    }

    @Then("Request to create address API")
    public void requestToCreateAddressAPI() {
        addressService.requestAddressCreateApi();
    }

    @Then("Verify create address response with status code {string}")
    public void verifyCreateAddressResponseWithStatusCode(String statusOk) {
        addressService.verifyCreateAddressResponse(statusOk);
    }

}
