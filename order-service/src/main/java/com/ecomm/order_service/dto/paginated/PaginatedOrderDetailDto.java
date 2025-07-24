package com.ecomm.order_service.dto.paginated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedOrderDetailDto {
    private long id;

    private int inventory;

    private Double productPrice;

    private String productName;

    private long productId;

    private int qty;

    private long odOrderId;
}
