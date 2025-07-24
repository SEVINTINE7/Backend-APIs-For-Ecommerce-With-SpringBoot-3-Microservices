package com.ecomm.order_service.dto.paginated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse {
    private PaginatedOrderDto oDto;
    private PaginatedOrderDetailDto odDto;
}
