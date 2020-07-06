package com.reven.onlinestore.product.service;

import com.reven.onlinestore.common.exception.EntityNotFoundException;
import com.reven.onlinestore.common.exception.InvalidParameterRequest;
import com.reven.onlinestore.common.model.Product;
import com.reven.onlinestore.product.model.FilterProductRequest;
import com.reven.onlinestore.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final LockRegistry lockRegistry;

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
    @SneakyThrows
    public List<Product> reserveProducts(List<Product> products) {
        log.info("Reserve stock for products: {}", products);
        products.stream().forEach(p -> {
            //Thread-safe
            Lock lock = lockRegistry.obtain(String.valueOf(p.getId()));
            try {
                boolean lockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
                if(lockAcquired) {
                    p.setUpdatedDate(Instant.now().toEpochMilli());
                    Product before = productRepository.findById(p.getId()).orElseThrow(
                            () -> new EntityNotFoundException("Product not found for id " + p.getId())
                    );
                    before.setQuantity(before.getQuantity() - p.getQuantity());
                    before.setUpdatedDate(Instant.now().toEpochMilli());
                    productRepository.save(before);
                    p.setPrice(before.getPrice()); // Correct price from DB
                }
            } catch (InterruptedException iex) {
                log.error("Concurrent handling error", iex);
            } catch (Exception ex) {
                log.error("Exception on ProductService.reserveProducts id: {}", p.getId(), ex);
                throw ex;
            } finally {
                lock.unlock();
            }
        });

        return products;
    }
}
