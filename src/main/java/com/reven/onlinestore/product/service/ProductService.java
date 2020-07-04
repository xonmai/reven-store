package com.reven.onlinestore.product.service;

import com.reven.onlinestore.common.exception.InvalidParameterRequest;
import com.reven.onlinestore.common.model.Product;
import com.reven.onlinestore.product.model.FilterProductRequest;
import com.reven.onlinestore.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> filterProduct(FilterProductRequest request) {
        if(request.isNotValid()) {
            throw new InvalidParameterRequest("Payload cannot be empty");
        }
        return productRepository.filterByCriteria(request);
    }

    @Transactional
    public void reserveProducts(List<Product> products) {
        log.info("Reserve stock for products: {}", products);
        try {
            products.stream().forEach(p -> {
                //Thread-safe
                //TODO Distributed lock for multi-instances
                synchronized (p.getId()) {
                    p.setUpdatedDate(Instant.now().toEpochMilli());
                    productRepository.reserveStock(p);
                }
            });
        } catch (Exception ex) {
            log.error("Exception on ProductService.reserveProducts", ex);
            throw ex;
        }
    }
}
