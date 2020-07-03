package com.reven.onlinestore.common.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
public class CustomerActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String customerName;

    @Column
    private String customerAction;

    @Column
    private String params;

    @Column
    private Long actionTime;

}
