package com.reven.onlinestore.order.service;

import com.reven.onlinestore.common.model.JmsTopic;
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
public class OrderCreateTask implements OrderTask {

    private final OrderRepository orderRepository;

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(Order order) {
        order.setOrderStatus(OrderStatus.ORDER_PROCESSING)
                .setCreatedDate(Instant.now().toEpochMilli())
                .setUpdatedDate(Instant.now().toEpochMilli());

        try {
            orderRepository.save(order);
            jmsTemplate.convertAndSend(JmsTopic.ORDER_CREATED_QUEUE, order.getOrderDetail());
        } catch (Exception ex) {
            log.error("Exception on OrderCreateTask", ex);
        }
    }
}
