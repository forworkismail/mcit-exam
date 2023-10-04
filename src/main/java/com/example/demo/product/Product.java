package com.example.demo.product;

import com.example.demo.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines; // Each product can appear in many order lines.
}