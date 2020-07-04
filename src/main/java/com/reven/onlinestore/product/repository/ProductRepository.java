package com.reven.onlinestore.product.repository;

import com.reven.onlinestore.product.model.FilterProductRequest;
import com.reven.onlinestore.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:#{#param.name} = '' OR p.name like %:#{#param.name}%) " +
            "AND (:#{#param.brand} = '' OR p.brand like %:#{#param.brand}%) " +
            "AND (:#{#param.color} = '' OR p.color like %:#{#param.color}%)"
    )
    List<Product> filterByCriteria(@Param("param") FilterProductRequest param);
}
