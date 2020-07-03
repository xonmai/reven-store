package com.reven.onlinestore.product.controller;

import com.reven.onlinestore.common.exception.EntityNotFoundException;
import com.reven.onlinestore.product.model.Product;
import com.reven.onlinestore.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        log.info("ProductController.getProduct({})", id);
        Optional<Product> result = productService.getById(id);
        return result.orElseThrow(
                () -> new EntityNotFoundException(String.format("Product '%s' not found", id)));
    }
}
