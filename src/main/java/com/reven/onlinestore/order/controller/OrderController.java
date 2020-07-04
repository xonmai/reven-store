package com.reven.onlinestore.order.controller;

import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.model.OrderCreateRequest;
import com.reven.onlinestore.order.model.OrderStatus;
import com.reven.onlinestore.order.model.OrderTask;
import com.reven.onlinestore.order.service.OrderCreateTask;
import com.reven.onlinestore.order.service.OrderProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProcessor orderProcessor;

    @PostMapping("/create")
    public Order createOrder(OrderCreateRequest request) {
        log.info("OrderController.createOrder('{}'): {}", request.getId(), request.getOrderDetail());
        Order created = new Order()
                .setId(request.getId())
                .setOrderDetail(request.getOrderDetail())
                .setTotalPrice(request.getTotalPrice())
                .setOrderStatus(OrderStatus.ORDER_CREATED)
                .setCreatedDate(Instant.now().toEpochMilli())
                .setUpdatedDate(Instant.now().toEpochMilli());

        LinkedList<OrderTask> tasks = new LinkedList<>();

        orderProcessor.setOrder(created);
        orderProcessor.setTasks(tasks);
        return orderProcessor.process();
    }

    @PostMapping("/{id}/checkout")
    public String checkoutOrder(@PathVariable("id") Long id) {


        return "success";
    }
}
