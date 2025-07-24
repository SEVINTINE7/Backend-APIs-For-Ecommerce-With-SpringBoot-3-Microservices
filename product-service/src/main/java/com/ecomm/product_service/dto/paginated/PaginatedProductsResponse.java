package com.ecomm.product_service.dto.paginated;

import com.ecomm.product_service.dto.InventoryDto;
import com.ecomm.product_service.dto.ProductDto;
import com.ecomm.product_service.dto.response.ProductSaveResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedProductsResponse {
    private List<PaginatedProductDto> paginatedProductDtos;
    private int dataCount;
}
