package com.example.OrderViewer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
