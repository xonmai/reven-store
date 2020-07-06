package com.reven.onlinestore.order.service;

import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }
}
