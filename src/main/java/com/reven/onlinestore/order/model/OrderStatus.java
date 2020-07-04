package com.reven.onlinestore.order.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  OrderStatus {

    ORDER_PROCESSING("Processing"),
    ORDER_CREATED("Created"),
    ORDER_CANCELLED("Cancelled"),
    ORDER_FAILED("Failed"),
    ORDER_CHECKOUT("Checkout");

    private final String name;
}
