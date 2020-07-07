package com.reven.onlinestore.order.service;

import com.reven.onlinestore.order.model.OrderTask;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Setter
@Service
@NoArgsConstructor
public class OrderProcessor {

    private LinkedList<OrderTask> tasks;

    public void process() {
        tasks.stream().forEach(t -> t.execute());
    }

}
