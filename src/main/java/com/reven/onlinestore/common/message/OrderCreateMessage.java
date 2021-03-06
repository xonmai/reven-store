package com.reven.onlinestore.common.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateMessage {

    private Long orderId;
    private String orderDetail;

}
