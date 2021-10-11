package ecom.dto.buyer.address.response;

import ecom.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 02:48
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateResponseDto {

    private Boolean success;

    private Address address;

}
