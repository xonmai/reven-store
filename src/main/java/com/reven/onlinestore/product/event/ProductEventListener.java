package com.reven.onlinestore.product.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.reven.onlinestore.common.message.OrderCreateMessage;
import com.reven.onlinestore.common.message.OrderUpdateMessage;
import com.reven.onlinestore.common.model.OrderEventTopic;
import com.reven.onlinestore.common.model.Product;
import com.reven.onlinestore.order.model.OrderStatus;
import com.reven.onlinestore.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductService productService;

    private final JmsTemplate jmsTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @JmsListener(destination = OrderEventTopic.ORDER_CREATED_QUEUE)
    public void orderCreatedEvent(OrderCreateMessage message) {
        try {
            log.info("Receive ORDER_CREATED_EVENT: {}", message);
            List<Product> products = mapper.readValue(message.getOrderDetail(), new TypeReference<List<Product>>(){});
            productService.reserveProducts(products);
            String orderDetail = mapper.writeValueAsString(products);
            Double totalPrice = products.stream()
                    .map(p -> p.getQuantity() * p.getPrice())
                    .reduce(0d, Double::sum);
            OrderUpdateMessage updateMessage = OrderUpdateMessage.builder()
                    .orderId(message.getOrderId())
                    .orderDetail(orderDetail)
                    .totalPrice(totalPrice)
                    .status(OrderStatus.ORDER_CREATED)
                    .build();
            jmsTemplate.convertAndSend(OrderEventTopic.ORDER_UPDATE_QUEUE, updateMessage);
        } catch (Exception ex) {
            log.error("Exception on ProductEventListener.orderCreatedEvent()", ex);
            //TODO send message to Order-Service to update Order status
        }

    }

}
