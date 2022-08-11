package ts4u.dto.buyer.order.request;

import ts4u.entity.Items;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
