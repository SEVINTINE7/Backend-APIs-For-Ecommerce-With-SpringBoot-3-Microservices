package com.ecomm.product_service.util.Mappers;

import com.ecomm.product_service.dto.CategoryDto;
import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.ProductDto;
import com.ecomm.product_service.entity.Category;
import com.ecomm.product_service.entity.Inventory;
import com.ecomm.product_service.entity.Product;
import com.ecomm.product_service.repository.CategoryRepo;
import com.ecomm.product_service.repository.InventoryRepo;
import com.ecomm.product_service.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class Mapper {

    private CategoryRepo categoryRepo;
    private ProductRepo productRepo;
    private InventoryRepo inventoryRepo;

    public ProductDto mapProductToProductDto(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getPName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory().getCategoryId(),
                product.getInventoryId().getInventoryId(),
                product.getCreatedTime(),
                product.getUpdatedTime()
        );
    }

    public Product mapProductDtoToProduct(ProductDto productDto){
        return new  Product(
                productDto.getPid(),
                productDto.getPName(),
                productDto.getPrice(),
                productDto.getDescription(),
                categoryRepo.getReferenceById(productDto.getCategory()),
                inventoryRepo.getReferenceById(productDto.getInventoryId()),
                productDto.getCreatedTime(),
                productDto.getUpdatedTime()
        );
    }

    public InventoryDto mapInventoryToInventoryDto(Inventory i){
        return new InventoryDto(
                i.getInventoryId(),
                i.getQuantity()
        );
    }

    public Inventory mapInventoryDtoToInventory(InventoryDto i){
        return new Inventory(
                i.getInventoryId(),
                i.getQuantity()
        );
    };

    public Category mapCategoryDtoToCategory(CategoryDto categoryDto){
        return new Category(
                categoryDto.getCategoryId(),
                categoryDto.getCategoryName(),
                categoryDto.getCategoryDescription()
        );
    }
    public CategoryDto mapCategoryToCategoryDto(Category category){
        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription()
        );
    }

    public List<CategoryDto> mapCategoryListToCategoryDtoList(List<Category> categoryList){
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category c: categoryList){
            CategoryDto cto = new CategoryDto(
                    c.getCategoryId(),
                    c.getCategoryName(),
                    c.getCategoryDescription()
            );
            categoryDtos.add(cto);
        }
        return categoryDtos;
    }

    public List<ProductDto> mapProductListToProductDtoList(List<Product> productList){
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p: productList){
            ProductDto pto = new ProductDto(
                    p.getProductId(),
                    p.getPName(),
                    p.getPrice(),
                    p.getDescription(),
                    p.getCategory().getCategoryId(),
                    p.getInventoryId().getInventoryId(),
                    p.getCreatedTime(),
                    p.getUpdatedTime()
            );
            productDtos.add(pto);
        }
        return productDtos;
    }

    public List<InventoryDto> mapInventoryListToInventoryDtoList(List<Inventory> inventories){
        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        for (Inventory i: inventories){
            InventoryDto ito = new InventoryDto(
                    i.getInventoryId(),
                    i.getQuantity()
            );
            inventoryDtoList.add(ito);
        }
        return inventoryDtoList;
    }

}
