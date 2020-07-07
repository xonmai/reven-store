package com.reven.onlinestore.order.service;

import com.reven.onlinestore.common.message.OrderCreateMessage;
import com.reven.onlinestore.common.model.OrderEventTopic;
import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.model.OrderStatus;
import com.reven.onlinestore.order.model.OrderTask;
import com.reven.onlinestore.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreateTask implements OrderTask {

    @Setter
    private Order order;

    private final OrderRepository orderRepository;

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute() {
        order.setOrderStatus(OrderStatus.ORDER_PROCESSING)
                .setCreatedDate(Instant.now().toEpochMilli())
                .setUpdatedDate(Instant.now().toEpochMilli());

        try {
            orderRepository.save(order);
            OrderCreateMessage message = OrderCreateMessage.builder()
                    .orderId(order.getId())
                    .orderDetail(order.getOrderDetail())
                    .build();
            jmsTemplate.convertAndSend(OrderEventTopic.ORDER_CREATED_QUEUE, message);
        } catch (Exception ex) {
            log.error("Exception on OrderCreateTask", ex);
        }
    }
}
