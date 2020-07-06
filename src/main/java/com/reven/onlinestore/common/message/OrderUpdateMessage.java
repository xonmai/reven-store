package com.reven.onlinestore.common.message;

import com.reven.onlinestore.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateMessage {

    private Long orderId;
    private String orderDetail;
    private Double totalPrice;
    private OrderStatus status;

}
