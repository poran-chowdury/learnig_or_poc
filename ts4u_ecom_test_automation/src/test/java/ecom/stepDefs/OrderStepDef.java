package ecom.stepDefs;

import cucumber.api.java.en.Then;
import ecom.service.BaseService;
import ecom.service.buyer.OrderService;

/**
 * @author TOWFIQUL ISLAM
 * @since 07/08/2021 02:44
 */

public class OrderStepDef extends BaseService {

    OrderService orderService = new OrderService();

    @Then("Prepare a new order request dto")
    public void prepareANewOrderRequestDto() {
        orderService.prepareOrderRequestDto();
    }

    @Then("Create an order using previous created order request dto")
    public void createAnOrderUsingPreviousCreatedProduct() {
        orderService.requestOrderCreateApi();
    }

    @Then("Verify create order response with status code {string}")
    public void verifyCreateOrderResponseWithStatusCode(String statusOk) {
        orderService.verifyCreateOrderResponse(statusOk);
    }

    @Then("Verify created order in DB")
    public void verifyCreatedOrderInDB() {
        orderService.verifyCreatedOrderInDB();
    }
}
