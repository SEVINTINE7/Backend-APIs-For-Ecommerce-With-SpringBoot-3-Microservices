package com.ecomm.product_service.dto.response;

import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.ProductDto;
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
