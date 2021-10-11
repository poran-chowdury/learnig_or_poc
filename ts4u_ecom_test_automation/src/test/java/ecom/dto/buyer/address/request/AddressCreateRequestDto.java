package ecom.dto.buyer.address.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 02:48
 */

@Data
@Builder
public class AddressCreateRequestDto {

    private String name;

    private String mobileNumber;

    private String state;

    private String city;

    private String zip;

    private String address;

}
