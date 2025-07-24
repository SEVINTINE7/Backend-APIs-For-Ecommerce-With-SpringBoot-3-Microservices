package com.ecomm.order_service.dto.requests;

import com.ecomm.order_service.dto.OrderDetailsDto;
import com.ecomm.order_service.entity.OrderDetails;
import com.ecomm.order_service.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequest {

    private Long orderId;

    private Date createdTime;

    private Date updatedTime;

    private String customer;

    private Long addressId;

    private Double total;

    private OrderStatus orderStatus;

    private Set<OrderDetailsDto> orderDetails;
}
