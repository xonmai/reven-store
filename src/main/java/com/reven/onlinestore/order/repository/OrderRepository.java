package com.reven.onlinestore.order.repository;

import com.reven.onlinestore.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
