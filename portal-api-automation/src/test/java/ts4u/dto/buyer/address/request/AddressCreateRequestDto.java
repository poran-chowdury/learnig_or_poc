package ts4u.dto.buyer.address.request;

import lombok.Builder;
import lombok.Data;

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
