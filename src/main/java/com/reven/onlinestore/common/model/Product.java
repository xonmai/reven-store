package com.reven.onlinestore.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String brand;

    @Column
    private String color;

    @Column
    private Long quantity;

    @Column
    private Double price;

    @Column
    private Long updatedDate;

}
