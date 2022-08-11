package ts4u.requestBody;

import com.google.gson.Gson;
import ts4u.dto.admin.product.request.ProductCreateRequestDto;
import ts4u.dto.buyer.address.request.AddressCreateRequestDto;
import ts4u.dto.buyer.auth.request.RegistrationRequestDto;
import ts4u.dto.buyer.auth.request.VerifyOtpRequestDto;
import ts4u.dto.buyer.order.request.OrderCreateRequestDto;
import ts4u.dto.request.LoginRequestDto;

public class BodyBuilder {

    public String getDynamicRequestBody(Object anyObject) {
        return new Gson().toJson(anyObject);
    }

    public String getRegistrationRequestBody(
            String firstname,
            String lastname,
            String email,
            String phone,
            String password) {

        return new Gson().toJson(
            RegistrationRequestDto.builder()
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .phone(phone)
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

    public String getResendOtpRequestBody(String email) {
        return new Gson().toJson(
                LoginRequestDto.builder()
                        .email(email)
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
