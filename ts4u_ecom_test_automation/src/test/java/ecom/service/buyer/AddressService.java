package ecom.service.buyer;

import ecom.dto.buyer.address.request.AddressCreateRequestDto;
import ecom.dto.buyer.address.response.GetAddressResponseDto;
import ecom.enums.AttachmentName;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.service.BaseService;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 02:46
 */

public class AddressService extends BaseService {

    protected Response response;
    protected AddressCreateRequestDto requestDto;

    public void prepareAddressRequestDto() {

        System.out.println("prepareAddressRequestDto");

        requestDto = AddressCreateRequestDto.builder()
            .name("test-address-name")
            .mobileNumber("1234567890")
            .state("NY")
            .city("NY")
            .zip("12346")
            .address("test-address")
            .build();
    }

    public void requestAddressCreateApi() {

        System.out.println("requestAddressCreateApi");

        response = requests.postRequest(
            EndPoints.createAddress,
            bodyBuilder.getAddressCreateRequestBody(requestDto)
        );

        Assert.assertEquals(StatusCode.CREATED.getStatusCode(), response.getStatusCode());
        validateAndAttachResponse(AttachmentName.CREATE_ADDRESS, response);
    }

    public void verifyCreateAddressResponse(String statusCode) {

        System.out.println("verifyCreateAddressResponse");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode));
    }

    public String getAddressId() {

        System.out.println("getAddress");

        response = requests.getRequest(EndPoints.getAddress, StringUtils.EMPTY);

        GetAddressResponseDto responseDto = response.body().as(GetAddressResponseDto.class);

        return responseDto.getAddresses().get(0).get_id();
    }

}
