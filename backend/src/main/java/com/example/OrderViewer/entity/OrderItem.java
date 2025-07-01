package com.example.OrderViewer.entity;


import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private String productName;

    private double quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
