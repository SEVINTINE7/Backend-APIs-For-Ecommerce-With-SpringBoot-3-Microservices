package com.ecomm.product_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "inventoryId",nullable = false)
    private Inventory inventoryId;

    @Column(nullable = false)
    private String pName;

    @Column(nullable = false)
    private Double price;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(nullable = false)
    private Date createdTime;

    @Column(nullable = false)
    private Date updatedTime;

    public Product(Long pid){
        this.productId = pid;
    }

    public Product(Long pid, String pName, Double price, String description, Category categoryId, Inventory inventoryId, Date createdTime, Date updatedTime) {
        this.productId = pid;
        this.pName = pName;
        this.price = price;
        this.description = description;
        this.category = categoryId;
        this.inventoryId = inventoryId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

}
