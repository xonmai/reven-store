package com.reven.onlinestore.product.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.reven.onlinestore.common.model.JmsTopic;
import com.reven.onlinestore.common.model.Product;
import com.reven.onlinestore.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductService productService;

    private ObjectMapper mapper = new ObjectMapper();

    @JmsListener(destination = JmsTopic.ORDER_CREATED_QUEUE)
    public void orderCreatedEvent(String message) {
        try {
            log.info("Receive ORDER_CREATED_EVENT: {}", message);
            List<Product> products = mapper.readValue(message, new TypeReference<List<Product>>(){});
            productService.reserveProducts(products);
        } catch (Exception ex) {
            log.error("Exception on ProductEventListener.orderCreatedEvent()", ex);
            //TODO send message to Order-Service to update Order status
        }

    }

}
