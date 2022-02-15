package enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttachmentName {

    CREATE_CUSTOMER("createCustomer"),
    VERIFY_OTP("verifyOtp"),
    LOGIN("login");
    private String attachmentName;
}
