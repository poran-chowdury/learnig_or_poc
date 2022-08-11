package ts4u.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttachmentName {

    CREATE_CUSTOMER("createCustomer"),
    VERIFY_OTP("verifyOtp"),
    LOGIN("login"),
    RESEND_OTP("resendOtp"),
    CREATE_PRODUCT("createProduct"),
    CREATE_ADDRESS("createAddress"),
    CREATE_ORDER("createOrder");

    private String attachmentName;
}
