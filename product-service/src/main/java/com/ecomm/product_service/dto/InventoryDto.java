package com.ecomm.product_service.dto;

import com.ecomm.product_service.entity.Product;
import com.ecomm.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

    private Long InventoryId;

    private int quantity;


}
