package com.ecomm.product_service.service;

import com.ecomm.product_service.Exceptions.ProductNotFoundException;
import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.response.ProductSaveResponse;

public interface InventoryService {
    ProductSaveResponse updateProductInventory(Long id, InventoryDto inventoryDto) throws ProductNotFoundException;

    InventoryDto getProductInventoryById(Long id) throws ProductNotFoundException;
}
