package com.ecomm.order_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long pid;

    private String pName;

    private Double price;

    private String description;

    private Long category;

    private Long inventoryId;

    private Date createdTime;

    private Date updatedTime;
}
