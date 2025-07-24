package com.ecomm.product_service.repository;

import com.ecomm.product_service.dto.paginated.PaginatedProductDtoInterface;
import com.ecomm.product_service.entity.Category;
import com.ecomm.product_service.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {



    @Query(value = "SELECT count(*) as quantity FROM product p INNER JOIN inventory i ON p.inventory_id = i.inventory_id", nativeQuery = true)
    int countAllProducts();


    @Query(value = "SELECT p.product_id as productId, p.created_time as createdTime, p.description as description, p.p_name as pName,p.price as price,p.updated_time as updatedTime, p.category_id as category,p.inventory_id as inventoryId, i.quantity FROM product p INNER JOIN inventory i ON p.inventory_id = i.inventory_id", nativeQuery = true)
    List<PaginatedProductDtoInterface> getAllProducts(PageRequest of);

    List<Product> findProductsByCategory(Category category);

    List<Product> findAllByProductIdIn(List<Long> pIds);
}