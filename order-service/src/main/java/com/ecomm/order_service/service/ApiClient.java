package com.ecomm.order_service.service;


import com.ecomm.order_service.dto.ProductSaveResponse;
import com.ecomm.order_service.exceptions.ProductNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8081/api/products", name = "PRODUCT-SERVICE")
public interface ApiClient {
    @PostMapping("get-products-by-multiple-ids")
    List<ProductSaveResponse> getProductsByIds(@RequestBody List<Long> pIds) throws ProductNotFoundException;
}
