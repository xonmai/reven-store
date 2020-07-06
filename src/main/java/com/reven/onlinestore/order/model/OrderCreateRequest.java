package com.reven.onlinestore.order.model;

import com.reven.onlinestore.common.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderCreateRequest {

    private Long orderId;
    @NonNull private List<Product> orderDetail;

}
