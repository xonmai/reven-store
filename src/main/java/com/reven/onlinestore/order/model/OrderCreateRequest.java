package com.reven.onlinestore.order.model;


import com.reven.onlinestore.common.model.Product;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class OrderCreateRequest {

    private Long id;
    @NonNull private List<Product> orderDetail;
    @NonNull private Double totalPrice;

}
