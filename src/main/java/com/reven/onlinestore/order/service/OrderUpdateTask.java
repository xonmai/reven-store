package com.reven.onlinestore.order.service;

import com.reven.onlinestore.common.message.OrderCreateMessage;
import com.reven.onlinestore.common.model.OrderEventTopic;
import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.model.OrderStatus;
import com.reven.onlinestore.order.model.OrderTask;
import com.reven.onlinestore.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderUpdateTask implements OrderTask {

    private final OrderRepository orderRepository;

    @Override
    public void execute(Order order) {
        try {
            order.setUpdatedDate(Instant.now().toEpochMilli());
            orderRepository.save(order);
        } catch (Exception ex) {
            log.error("Exception on OrderUpdateTask", ex);
        }
    }
}
