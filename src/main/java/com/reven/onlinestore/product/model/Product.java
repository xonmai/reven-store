package com.reven.onlinestore.product.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class Product {

    private Long id;

    private String name;

    private String description;

    private Long quantity;

    private Double price;

    private String metadata;

    private Long updatedDate;

}
