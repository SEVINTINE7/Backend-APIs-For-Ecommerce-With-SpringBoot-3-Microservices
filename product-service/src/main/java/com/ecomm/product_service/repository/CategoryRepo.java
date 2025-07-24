package com.ecomm.product_service.repository;

import com.ecomm.product_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
