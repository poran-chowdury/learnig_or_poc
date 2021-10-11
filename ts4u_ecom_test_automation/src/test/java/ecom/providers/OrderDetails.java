package ecom.providers;

import ecom.dto.buyer.order.response.OrderCreateResponseDto;
import ecom.entity.Order;
import io.restassured.response.Response;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 07/08/2021 01:05
 */

public class OrderDetails {

    private static final HashMap<String, Object> orderMap = new HashMap<>();

    private OrderCreateResponseDto responseDto;

    public static HashMap<String, Object> getOrderMap() {
        return orderMap;
    }

    private static void addValue(String key, Object value) {
        if (orderMap.containsKey(key)) {
            orderMap.replace(key, value);
        } else {
            orderMap.put(key, value);
        }
    }

    public void setResponse(Response response) {
        responseDto = response.getBody().as(OrderCreateResponseDto.class);
        setOrderMapValue(responseDto.getOrder());
    }

    private void setOrderMapValue(Order order) {
        addValue("orderId", order.get_id());
    }
}
