package com.reven.onlinestore.product.service;

import com.reven.onlinestore.common.exception.InvalidParameterRequest;
import com.reven.onlinestore.product.model.FilterProductRequest;
import com.reven.onlinestore.common.model.Product;
import com.reven.onlinestore.product.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> filterProduct(FilterProductRequest request) {
        if(request.isValid()) {
            throw new InvalidParameterRequest("Payload cannot be empty");
        }
        return productRepository.filterByCriteria(request);
    }
}
