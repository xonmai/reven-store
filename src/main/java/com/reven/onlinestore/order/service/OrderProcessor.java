package com.reven.onlinestore.order.service;

import com.reven.onlinestore.order.model.Order;
import com.reven.onlinestore.order.model.OrderTask;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Setter
@Service
@NoArgsConstructor
public class OrderProcessor {

    private Order order;

    private LinkedList<OrderTask> tasks;

    public Order process() {
        tasks.stream().forEach(t -> t.execute(this.order));
        return this.order;
    }

}
