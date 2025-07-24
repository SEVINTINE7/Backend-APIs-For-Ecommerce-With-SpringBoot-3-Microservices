package com.ecomm.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;

    private int quantity;

    @OneToOne(mappedBy = "inventoryId")
    private Product productId;

    public Inventory(Long inventoryId, int quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }

}
