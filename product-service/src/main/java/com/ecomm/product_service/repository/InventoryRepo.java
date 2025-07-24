package com.ecomm.product_service.repository;

import com.ecomm.product_service.entity.Inventory;
import com.ecomm.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    List<Inventory> findAllByInventoryIdIn(List<Long> inventoryIds);
}
