package com.example.demo.orderline;

import com.example.demo.order.Order;
import com.example.demo.product.Product;
import jakarta.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // Each order line belongs to one order.

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // Each order line references one product.

    private int quantity;
    private double purchasePrice; // This could represent the price at which the product was bought.
}