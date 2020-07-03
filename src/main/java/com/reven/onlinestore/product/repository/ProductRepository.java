package com.reven.onlinestore.product.repository;

import com.reven.onlinestore.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
