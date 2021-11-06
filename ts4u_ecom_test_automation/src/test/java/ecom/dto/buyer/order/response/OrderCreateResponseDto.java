package ecom.dto.buyer.order.response;

import ecom.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 22:43
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateResponseDto {

    private Boolean success;

    private Order order;

}
