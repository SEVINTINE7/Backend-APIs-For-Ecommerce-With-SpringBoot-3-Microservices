package com.ecomm.order_service.dto.paginated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedOrderResponse {
    List<PaginatedResponse> response;
    private int dataCount;
}
