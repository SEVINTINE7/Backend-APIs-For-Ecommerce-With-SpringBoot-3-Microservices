package com.ecomm.product_service.dto.requests;

import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveRequest {
    private ProductDto productDto;
    private InventoryDto inventoryDto;
}
