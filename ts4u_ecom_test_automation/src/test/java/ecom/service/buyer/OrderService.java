package ecom.service.buyer;

import ecom.dto.buyer.order.request.OrderCreateRequestDto;
import ecom.entity.Items;
import ecom.entity.Variations;
import ecom.enums.AttachmentName;
import ecom.enums.MongoDBCollection;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.providers.OrderDetails;
import ecom.providers.ProductDetails;
import ecom.service.BaseService;
import ecom.service.DBService;
import ecom.service.admin.ProductService;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 02:46
 */

public class OrderService extends BaseService {

    protected Response response;
    protected OrderCreateRequestDto requestDto;
    protected AddressService addressService = new AddressService();
    protected ProductService productService = new ProductService();
    private final OrderDetails orderDetails = new OrderDetails();


    public void prepareOrderRequestDto() {

        System.out.println("prepareOrderRequestDto");

        requestDto = OrderCreateRequestDto.builder()
            .addressId(addressService.getAddressId())
            .totalAmount("20040")
            .items(getItems())
            .paymentMethod("cod")
            .tax(StringUtils.EMPTY)
            .shipping(StringUtils.EMPTY)
            .transactionId(StringUtils.EMPTY)
            .build();
    }

    public void requestOrderCreateApi() {

        System.out.println("requestOrderCreateApi");

        response = requests.postRequest(
            EndPoints.createOrder,
            bodyBuilder.getOrderCreateRequestBody(requestDto)
        );

        orderDetails.setResponse(response);

        Assert.assertEquals(
            response.body().asPrettyString(),
            StatusCode.CREATED.getStatusCode(),
            response.getStatusCode()
        );
        validateAndAttachResponse(AttachmentName.CREATE_ORDER, response);
    }

    public void verifyCreateOrderResponse(String statusCode) {

        System.out.println("verifyCreateOrderResponse");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode));
    }

    public void verifyCreatedOrderInDB() {
        System.out.println("verifyCreatedOrderInDB");
        System.out.println(OrderDetails.getOrderMap().get("orderId").toString());
        boolean isDataExist = DBService.isDBDataExist(
            MongoDBCollection.ORDERS,
            eq("_id", new ObjectId(OrderDetails.getOrderMap().get("orderId").toString()))
        );

        Assert.assertTrue(isDataExist);

    }

    private List<Items> getItems() {
        String productId = productService.getProductId();
        System.out.println(productId);

        String productSlug = productService.getProductSlug();
        System.out.println(productSlug);

        String productName = productService.getProductName();
        System.out.println(productName);


        List<Items> itemsList = new ArrayList<>();
        itemsList.add(
            Items.builder()
                .thumbnail("https://res.cloudinary.com/shimul/image/upload/v1617901875/4BomcFvwl-xiaomi-mi-cc9-pro-4.jpg")
                .productSlug(productSlug)
                .productName(productName)
                .payablePrice("20000")
                .purchasedQty(1)
                .variations(Variations.builder().ram("4").rom("128").build())
                .campaign(null)
                .productId(productId)
                .build()
        );
        return itemsList;
    }

}
