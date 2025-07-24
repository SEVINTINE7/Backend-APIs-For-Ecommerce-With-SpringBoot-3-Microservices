package com.ecomm.product_service.service.impl;

import com.ecomm.product_service.Exceptions.ProductNotFoundException;
import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.response.ProductSaveResponse;
import com.ecomm.product_service.entity.Inventory;
import com.ecomm.product_service.entity.Product;
import com.ecomm.product_service.repository.InventoryRepo;
import com.ecomm.product_service.repository.ProductRepo;
import com.ecomm.product_service.service.InventoryService;
import com.ecomm.product_service.util.Mappers.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepo inventoryRepo;
    private ProductRepo productRepo;
    private Mapper mapper;

    @Override
    public ProductSaveResponse updateProductInventory(Long id, InventoryDto inventoryDto) throws ProductNotFoundException {
        if (productRepo.existsById(id)) {
            Product product = productRepo.getReferenceById(id);
            Inventory inventory = inventoryRepo.getReferenceById(product.getInventoryId().getInventoryId());
            inventory.setQuantity(inventoryDto.getQuantity());
            Inventory updatedInventory = inventoryRepo.save(inventory);
            return new ProductSaveResponse(
                    mapper.mapProductToProductDto(product),
                    mapper.mapInventoryToInventoryDto(updatedInventory)
            );
        }
        throw new ProductNotFoundException("Can't update inventory because the product with " + id + " has not found");
    }

    @Override
    public InventoryDto getProductInventoryById(Long id) throws ProductNotFoundException {
        if (productRepo.existsById(id)){
            Product product = productRepo.getReferenceById(id);
            Inventory inventory = inventoryRepo.getReferenceById(product.getInventoryId().getInventoryId());
            return mapper.mapInventoryToInventoryDto(inventory);
        } throw new ProductNotFoundException("No inventory found because the product with " + id + " has not found");
    }
}
