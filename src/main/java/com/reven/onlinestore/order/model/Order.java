package com.reven.onlinestore.order.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String orderDetail;

    @Column
    private OrderStatus orderStatus;

    @Column
    private Double totalPrice;

    @Column
    private Long createdDate;

    @Column
    private Long updatedDate;
}
