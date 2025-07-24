package com.ecomm.product_service.service.impl;

import com.ecomm.product_service.Exceptions.CategoryNotFoundException;
import com.ecomm.product_service.dto.CategoryDto;
import com.ecomm.product_service.entity.Category;
import com.ecomm.product_service.entity.Product;
import com.ecomm.product_service.repository.ProductRepo;
import com.ecomm.product_service.util.Mappers.Mapper;
import com.ecomm.product_service.repository.CategoryRepo;
import com.ecomm.product_service.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private ProductRepo productRepo;
    private Mapper mapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        return mapper.mapCategoryToCategoryDto(categoryRepo.save(mapper.mapCategoryDtoToCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto1) throws CategoryNotFoundException {
        if (categoryRepo.existsById(id)){
            Category category = categoryRepo.getReferenceById(id);
            category.setCategoryName(categoryDto1.getCategoryName());
            category.setCategoryDescription(categoryDto1.getCategoryDescription());
            return mapper.mapCategoryToCategoryDto(categoryRepo.save(category));
        }
        throw new CategoryNotFoundException("Selected Category is not existed.");
    }

    @Override
    public String deleteCategory(Long id) throws CategoryNotFoundException {
        if (categoryRepo.existsById(id) && id != 404L){
            Category category = categoryRepo.getReferenceById(id);
            List<Product> productList = productRepo.findProductsByCategory(category);
            List<Product> updatedProductList = new ArrayList<>();
            for (Product product: productList){
                product.setCategory(categoryRepo.getReferenceById(404L));
                updatedProductList.add(product);
            }
            productRepo.saveAll(updatedProductList);
            categoryRepo.deleteById(id);
            return "Category is deleted but the products associated with the "+id+"-category is moved to default category.";
        }
        throw new CategoryNotFoundException(id +" category is not existed.");
    }

    @Override
    public CategoryDto getCategoryById(Long id) throws CategoryNotFoundException {
        if (categoryRepo.existsById(id) && id != 404L){
            return mapper.mapCategoryToCategoryDto(categoryRepo.getReferenceById(id));
        }
        throw new CategoryNotFoundException(id +" category is not existed.");
    }

    @Override
    @Transactional
    public String deleteCategoryWithProducts(Long id) throws CategoryNotFoundException {
        if (categoryRepo.existsById(id) && id != 404L){
            Category category = categoryRepo.getReferenceById(id);
            List<Product> products = productRepo.findProductsByCategory(category);
            productRepo.deleteAll(products);
            categoryRepo.deleteById(id);
            return "Category is deleted along with it's products";
        }
        throw new CategoryNotFoundException(id +" category is not existed.");
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepo.findAll();
        return mapper.mapCategoryListToCategoryDtoList(categoryList);
    }

}
