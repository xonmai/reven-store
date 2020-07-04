package com.reven.onlinestore.order.service;

import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.model.OrderStatus;
import com.reven.onlinestore.order.model.OrderTask;
import com.reven.onlinestore.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderCreateTask implements OrderTask {

    private final OrderRepository orderRepository;

    @Override
    public void execute(Order order) {
        order.setOrderStatus(OrderStatus.ORDER_CREATED)
                .setCreatedDate(Instant.now().toEpochMilli())
                .setUpdatedDate(Instant.now().toEpochMilli());

        orderRepository.save(order);
    }
}
