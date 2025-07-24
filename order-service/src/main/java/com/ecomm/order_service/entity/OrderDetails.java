package com.ecomm.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "detail_id")
    private Long detailsId;

    @Column(name = "qty", length = 100, nullable = false)
    private double qty;

    private Long productId;
    private String productName;
    private Double productPrice;
    private int inventory;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orders;
}
