package com.ecomm.product_service.controller;

import com.ecomm.product_service.Exceptions.CategoryNotFoundException;
import com.ecomm.product_service.dto.CategoryDto;
import com.ecomm.product_service.service.impl.CategoryServiceImpl;
import com.ecomm.product_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryServiceImpl categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<StandardResponse> saveCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategoryDto = categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", savedCategoryDto),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<StandardResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto1) throws CategoryNotFoundException {
        CategoryDto categoryDto = categoryService.updateCategory(id, categoryDto1);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", categoryDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<StandardResponse> deleteCategory(@PathVariable Long id) throws CategoryNotFoundException {
        String message = categoryService.deleteCategory(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/get-category-by-id/{id}")
    public ResponseEntity<StandardResponse> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", categoryDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-category-with-products/{id}")
    public ResponseEntity<StandardResponse> deleteCategoryWithProducts(@PathVariable Long id) throws CategoryNotFoundException {
        String message = categoryService.deleteCategoryWithProducts(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<StandardResponse> getAllCategories(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", categoryDtos),
                HttpStatus.OK
        );
    }

}
