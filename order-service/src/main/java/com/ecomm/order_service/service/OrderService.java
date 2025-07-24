package com.ecomm.order_service.service;

import com.ecomm.order_service.dto.OrderDto;
import com.ecomm.order_service.dto.paginated.PaginatedOrderResponse;
import com.ecomm.order_service.entity.enums.OrderStatus;
import com.ecomm.order_service.exceptions.ProductNotFoundException;

import java.util.Set;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto) throws ProductNotFoundException;

    Set<OrderDto> getOrdersByCusId(String cusId);

    Set<OrderDto> getAllOrdersByStatus(OrderStatus status);

    Set<OrderDto> getOrdersWithCusAndStatus(String cusId, OrderStatus status);

    OrderDto cancelOrder(Long orderId);

    String deleteOrderById(Long orderId);

    OrderDto changeOrderStatus(OrderStatus status, Long orderId);

    PaginatedOrderResponse getAllOrders(int page, int size);
}
