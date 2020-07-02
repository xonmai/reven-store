package com.reven.onlinestore.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/product")
public class ProductController {

    @GetMapping
    public String getProduct() {
        return "Hello";
    }
}
