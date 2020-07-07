package com.reven.onlinestore.order.event;

import com.reven.onlinestore.common.exception.EntityNotFoundException;
import com.reven.onlinestore.common.message.OrderUpdateMessage;
import com.reven.onlinestore.common.model.OrderEventTopic;
import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.repository.OrderRepository;
import com.reven.onlinestore.order.service.OrderUpdateTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderRepository orderRepository;

    private final OrderUpdateTask updateTask;

    @JmsListener(destination = OrderEventTopic.ORDER_UPDATE_QUEUE)
    public void orderUpdatedEvent(OrderUpdateMessage message) {
        log.info("Receive ORDER_UPDATE_QUEUE: {}", message);
        try {
            Order order = orderRepository.findById(message.getOrderId()).orElseThrow(
                    () -> new EntityNotFoundException("Failed to update order Id " + message.getOrderId())
            );
            order.setOrderDetail(message.getOrderDetail())
                    .setOrderStatus(message.getStatus())
                    .setTotalPrice(message.getTotalPrice());
            updateTask.setOrder(order);
            updateTask.execute();
        } catch (Exception ex) {
            log.error("Exception on orderUpdatedEvent", ex);
        }
    }
}
