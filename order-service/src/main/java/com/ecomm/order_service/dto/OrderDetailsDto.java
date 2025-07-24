package com.ecomm.order_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {

    private Long id;
    private double qty;
    private Long productId;
    private String productName;
    private Double productPrice;
    private int inventory;
    private Long orderId;
}
