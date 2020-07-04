package com.reven.onlinestore.order.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreateRequest {

    private Long id;

    private String orderDetail;

    private Double totalPrice;

}
