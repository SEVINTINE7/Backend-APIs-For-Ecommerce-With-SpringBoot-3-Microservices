package com.ecomm.product_service.dto.paginated;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PaginatedProductDto {
    private Long pid;

    private String pName;

    private Double price;

    private String description;

    private Long category;

    private Long inventoryId;

    private Date createdTime;

    private Date updatedTime;

    private int quantity;
}
