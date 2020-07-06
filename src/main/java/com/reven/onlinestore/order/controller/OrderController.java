package com.reven.onlinestore.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.model.OrderCreateRequest;
import com.reven.onlinestore.order.model.OrderStatus;
import com.reven.onlinestore.order.model.OrderTask;
import com.reven.onlinestore.order.service.OrderCreateTask;
import com.reven.onlinestore.order.service.OrderProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.LinkedList;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProcessor orderProcessor;

    private final OrderCreateTask orderCreateTask;

    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderCreateRequest request) throws JsonProcessingException {
        log.info("OrderController.createOrder('{}'): {}", request.getOrderId(), request.getOrderDetail());
        ObjectMapper mapper = new ObjectMapper();
        String orderDetail = mapper.writeValueAsString(request.getOrderDetail());
        Order created = new Order()
                .setId(request.getOrderId())
                .setOrderDetail(orderDetail);

        LinkedList<OrderTask> tasks = new LinkedList<>();
        tasks.add(orderCreateTask);

        orderProcessor.setOrder(created);
        orderProcessor.setTasks(tasks);
        return orderProcessor.process();
    }

    @PostMapping("/{id}/checkout")
    public String checkoutOrder(@PathVariable("id") Long id) {


        return "success";
    }
}
