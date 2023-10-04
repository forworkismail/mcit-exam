package com.example.demo.order;

import com.example.demo.orderline.OrderLine;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
}