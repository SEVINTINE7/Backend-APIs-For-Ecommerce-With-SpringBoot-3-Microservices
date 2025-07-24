package com.ecomm.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveResponse {
    private ProductDto productDto;
    private InventoryDto inventoryDto;
}
