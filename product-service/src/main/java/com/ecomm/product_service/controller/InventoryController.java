package com.ecomm.product_service.controller;

import com.ecomm.product_service.Exceptions.ProductNotFoundException;
import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.response.ProductSaveResponse;
import com.ecomm.product_service.service.InventoryService;
import com.ecomm.product_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @PutMapping("/update-product-inventory/{id}")
    public ResponseEntity<StandardResponse> updateProductInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) throws ProductNotFoundException {
        ProductSaveResponse response = inventoryService.updateProductInventory(id, inventoryDto);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", response),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-product-invenvory-by-id/{id}")
    public ResponseEntity<StandardResponse> getProductInventoryById(@PathVariable Long id) throws ProductNotFoundException {
        InventoryDto inventoryDto = inventoryService.getProductInventoryById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", inventoryDto),
                HttpStatus.OK
        );
    }
}
