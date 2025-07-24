package com.ecomm.product_service.service;

import com.ecomm.product_service.Exceptions.CategoryNotFoundException;
import com.ecomm.product_service.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto1) throws CategoryNotFoundException;

    String deleteCategory(Long id) throws CategoryNotFoundException;

    CategoryDto getCategoryById(Long id) throws CategoryNotFoundException;

    String deleteCategoryWithProducts(Long id) throws CategoryNotFoundException;

    List<CategoryDto> getAllCategories();
}
