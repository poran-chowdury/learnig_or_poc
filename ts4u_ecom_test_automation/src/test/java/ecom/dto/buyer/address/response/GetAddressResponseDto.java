package ecom.dto.buyer.address.response;

import ecom.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author TOWFIQUL ISLAM
 * @since 08/08/2021 03:54
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAddressResponseDto {

    private List<Address> addresses;

    private String message;

}
