package com.reven.onlinestore.order.model;


import com.reven.onlinestore.common.model.Product;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderCreateRequest {

    private Long id;
    @NonNull private List<Product> orderDetail;
    @NonNull private Double totalPrice;

}
