package com.reven.onlinestore.common.message;

import lombok.Builder;

@Builder
public class OrderCreateMessage {

    private Long orderId;
    private String orderDetails;

}
