package com.reven.onlinestore.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderCreateRequest {

    private Long orderId;
    @NonNull private List<OrderDetail> orderDetail;

    @Data
    private static class OrderDetail {
        private Long id;
        private Long quantity;
    }

}
