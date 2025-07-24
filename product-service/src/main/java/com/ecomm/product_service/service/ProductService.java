package com.ecomm.product_service.service;

import com.ecomm.product_service.Exceptions.ProductNotFoundException;
import com.ecomm.product_service.dto.ProductDto;
import com.ecomm.product_service.dto.paginated.PaginatedProductsResponse;
import com.ecomm.product_service.dto.requests.ProductSaveRequest;
import com.ecomm.product_service.dto.response.ProductSaveResponse;
import com.ecomm.product_service.entity.Product;

import java.util.List;

public interface ProductService {
    ProductSaveResponse saveProduct(ProductSaveRequest productSaveRequest);

    ProductDto getProductById(Long id);

    ProductDto updateProduct(Long pid, ProductDto productDto) throws ProductNotFoundException;

    PaginatedProductsResponse getAllProducts(int page, int size);

    String deleteProductById(Long id);

    List<ProductSaveResponse> getProductsByIds(List<Long> pIds) throws ProductNotFoundException;
}
