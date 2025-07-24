package com.ecomm.product_service.dto.paginated;

import com.ecomm.product_service.entity.Category;
import com.ecomm.product_service.entity.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public interface PaginatedProductDtoInterface {

//    private Long productId;
//    private Date createdTime;
//    private String description;
//    private String pName;
//    private Double price;
//    private Date updatedTime;
//    private Category category;
//    private Inventory inventoryId;
//    private int quantity;

    Long getProductId();

    Date getCreatedTime();

    String getDescription();

    String getpName();

    Double getPrice();

    Date getUpdatedTime();

    Long getCategory();

    Long getInventoryId();

    int getQuantity();
}
