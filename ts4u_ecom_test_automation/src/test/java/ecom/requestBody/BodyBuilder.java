package ecom.requestBody;

import com.google.gson.Gson;
import ecom.dto.admin.product.request.ProductCreateRequestDto;
import ecom.dto.buyer.address.request.AddressCreateRequestDto;
import ecom.dto.buyer.auth.request.RegistrationRequestDto;
import ecom.dto.buyer.auth.request.VerifyOtpRequestDto;
import ecom.dto.buyer.order.request.OrderCreateRequestDto;
import ecom.dto.request.LoginRequestDto;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:12
 */

public class BodyBuilder {

    public String getDynamicRequestBody(Object anyObject) {
        return new Gson().toJson(anyObject);
    }

    public String getRegistrationRequestBody(String name, String email, String password) {
        return new Gson().toJson(
            RegistrationRequestDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .build()
        );
    }

    public String getVerifyOtpRequestBody(String otp, String email) {
        return new Gson().toJson(
            VerifyOtpRequestDto.builder()
                .otp(otp)
                .email(email)
                .build()
        );
    }

    public String getLoginRequestBody(String email, String password) {
        return new Gson().toJson(
            LoginRequestDto.builder()
                .email(email)
                .password(password)
                .build()
        );
    }

    public String getProductCreateRequestBody(ProductCreateRequestDto requestDto) {
        return new Gson().toJson(requestDto);
    }

    public String getAddressCreateRequestBody(AddressCreateRequestDto requestDto) {
        return new Gson().toJson(requestDto);
    }

    public String getOrderCreateRequestBody(OrderCreateRequestDto requestDto) {
        return new Gson().toJson(requestDto);
    }

}
