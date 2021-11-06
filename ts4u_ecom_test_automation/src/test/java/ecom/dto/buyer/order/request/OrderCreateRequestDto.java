package ecom.dto.buyer.order.request;

import ecom.entity.Items;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 22:43
 */

@Data
@Builder
public class OrderCreateRequestDto {

    private String addressId;

    private String totalAmount;

    private List<Items> items;

    private String paymentMethod;

    private String tax;

    private String shipping;

    private String transactionId;

}
