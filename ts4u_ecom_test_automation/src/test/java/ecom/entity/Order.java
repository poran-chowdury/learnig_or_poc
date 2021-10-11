package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 23:23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Double paidAmount;

    private Double tax;

    private Double shipping;

    private String transactionId;

    private String orderStatus;

    private String _id;

    private String invoice;

    private Object variations;

    private String user;

    private String addressId;

    private Double totalAmount;

    private List<Items> items;

    private String paymentStatus;

    private List<OrderHistory> orderHistories;

    private String paymentMethod;

    private String createdAt;

    private String updatedAt;

    private String __v;
}
