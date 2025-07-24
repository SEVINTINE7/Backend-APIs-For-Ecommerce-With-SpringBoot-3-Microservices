package com.ecomm.product_service.controller;

import com.ecomm.product_service.Exceptions.ProductNotFoundException;
import com.ecomm.product_service.dto.ProductDto;
import com.ecomm.product_service.dto.paginated.PaginatedProductsResponse;
import com.ecomm.product_service.dto.requests.ProductSaveRequest;
import com.ecomm.product_service.dto.response.ProductSaveResponse;
import com.ecomm.product_service.service.impl.ProductServiceImpl;
import com.ecomm.product_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {

    private ProductServiceImpl productService;

    @PostMapping("/save-product")
    public ResponseEntity<StandardResponse> saveProduct(@RequestBody ProductSaveRequest saveDto) {
        ProductSaveResponse savedProduct = productService.saveProduct(saveDto);
        if (savedProduct.getProductDto() != null && savedProduct.getInventoryDto() != null) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", savedProduct),
                    HttpStatus.CREATED
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(500, "Cannot save product due to an internal server error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<StandardResponse> getProduct(@PathVariable Long id) { // works
        ProductDto productDto = productService.getProductById(id);
        if (productDto.getPid() != null) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", productDto),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(204, "Product not found", null),
                    HttpStatus.OK
            );
        }
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<StandardResponse> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) throws ProductNotFoundException { // works
        ProductDto updatedProductDto = productService.updateProduct(id, productDto);
        if (updatedProductDto.getPid() != null) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", productDto),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(204, "Product not found", null),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(path = "/get-all-products", params = {"page", "size"})
    public ResponseEntity<StandardResponse> getProducts(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedProductsResponse p = productService.getAllProducts(page, size);
        return new ResponseEntity<>(
                new StandardResponse(201, "Success", p),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete-product-by-id/{id}")
    public ResponseEntity<StandardResponse> deleteProductById(@PathVariable Long id) {
        String message = productService.deleteProductById(id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.NO_CONTENT
        );
    }

    @PostMapping("get-products-by-multiple-ids")
    public List<ProductSaveResponse> getProductsByIds(@RequestBody List<Long> pIds) throws ProductNotFoundException {
        return productService.getProductsByIds(pIds);
    }
}
