package com.ecomm.order_service.service.impl;

import com.ecomm.order_service.dto.OrderDetailsDto;
import com.ecomm.order_service.dto.OrderDto;
import com.ecomm.order_service.dto.ProductSaveResponse;
import com.ecomm.order_service.dto.paginated.*;
import com.ecomm.order_service.entity.Order;
import com.ecomm.order_service.entity.OrderDetails;
import com.ecomm.order_service.entity.enums.OrderStatus;
import com.ecomm.order_service.exceptions.ProductNotFoundException;
import com.ecomm.order_service.repository.OrderDetailsRepo;
import com.ecomm.order_service.repository.OrderRepository;
import com.ecomm.order_service.service.ApiClient;
import com.ecomm.order_service.service.OrderService;
import com.ecomm.order_service.util.mappers.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository repo;
    private OrderDetailsRepo ODRepo;
    private Mapper mapper;
    private ApiClient apiClient;

    @Override
    @Transactional
    public OrderDto saveOrder(OrderDto orderDto) throws ProductNotFoundException {
        Order savedOrder = repo.save(mapper.mapOrderDtoToOrder(orderDto));

        if (repo.existsById(savedOrder.getOrderId())) {

            Set<OrderDetailsDto> orderDetailsSet = orderDto.getOrderDetails();
            List<Long> pIds = orderDetailsSet.stream()
                    .map(OrderDetailsDto::getProductId).collect(Collectors.toList());

            List<ProductSaveResponse> productDtos = apiClient.getProductsByIds(pIds);
            List<OrderDetails> savingOD = new ArrayList<>();

            for (OrderDetailsDto o : orderDetailsSet) {
                ProductSaveResponse product = productDtos.stream()
                        .filter(p -> p.getProductDto().getPid().equals(o.getProductId()))
                        .findFirst()
                        .orElse(null);
                if (product != null) {
                    o.setOrderId(savedOrder.getOrderId());
                    o.setProductName(product.getProductDto().getPName());
                    o.setProductPrice(product.getProductDto().getPrice());
                    o.setInventory(product.getInventoryDto().getQuantity());
                    savingOD.add(mapper.mapOrderDetailsDtoToOrderDetailsSp(o, savedOrder));
                }
            }

            List<OrderDetails> savedOrderDetails = ODRepo.saveAll(savingOD);

            Set<OrderDetailsDto> savedOrderDetailsDto = new HashSet<>(mapper.mapOrderDetailsListToOrderDetailsDtoList(savedOrderDetails));
            OrderDto savedOrderDto = mapper.mapOrderToOrderDto(savedOrder);
            savedOrderDto.setOrderDetails(savedOrderDetailsDto);
            return savedOrderDto;
        } else {
            throw new ProductNotFoundException("requested products not found");
        }
    }

    @Override
    public Set<OrderDto> getOrdersByCusId(String cusId) {

        List<Order> orders = repo.findAllByCustomer(cusId);
        List<Order> sortedOrders = orders.stream()
                .sorted((o1, o2) -> o2.getCreatedTime().compareTo(o1.getCreatedTime()))
                .toList();
        List<Long> orderIds = sortedOrders.stream()
                .map(Order::getOrderId)
                .toList();
        List<OrderDetails> orderDetailsList = ODRepo.findAllByOrdersIn(orderIds);

        Set<OrderDto> newOrderDtoSet = new HashSet<>();
        for (Order order : sortedOrders) {
            List<OrderDetails> orderDetails = orderDetailsList.stream()
                    .filter(orderDetails1 -> orderDetails1.getOrders().equals(order))
                    .toList();
            OrderDto orderDto = mapper.mapOrderToOrderDto(order);
            Set<OrderDetailsDto> orderDetailsDtoSet = new HashSet<>(mapper.mapOrderDetailsListToOrderDetailsDtoList(orderDetails));
            orderDto.setOrderDetails(orderDetailsDtoSet);
            newOrderDtoSet.add(orderDto);
        }
        return newOrderDtoSet;
    }

    @Override
    public Set<OrderDto> getAllOrdersByStatus(OrderStatus status) {
        List<Order> orders = repo.findAllByOrderStatus(status);
        List<Long> orderIds = orders.stream()
                .map(Order::getOrderId)
                .toList();

        List<OrderDetails> orderDetailsList = ODRepo.findAllByOrdersIn(orderIds);

        List<Order> sortedOrders = orders.stream()
                .sorted((o1, o2) -> o2.getCreatedTime().compareTo(o1.getCreatedTime()))
                .toList();

        Set<OrderDto> newOrderDtoSet = new HashSet<>();
        for (Order order : sortedOrders) {
            List<OrderDetails> orderDetails = orderDetailsList.stream()
                    .filter(orderDetails1 -> orderDetails1.getOrders().equals(order))
                    .toList();
            OrderDto orderDto = mapper.mapOrderToOrderDto(order);
            Set<OrderDetailsDto> orderDetailsDtoSet = new HashSet<>(mapper.mapOrderDetailsListToOrderDetailsDtoList(orderDetails));
            orderDto.setOrderDetails(orderDetailsDtoSet);
            newOrderDtoSet.add(orderDto);
        }
        return newOrderDtoSet;
    }

    @Override
    public Set<OrderDto> getOrdersWithCusAndStatus(String cusId, OrderStatus status) {
        List<Order> orders = repo.findAllByCustomerAndStatus(cusId, status.ordinal());
        List<Long> orderIds = orders.stream()
                .map(Order::getOrderId)
                .toList();
        List<OrderDetails> orderDetailsList = ODRepo.findAllByOrdersIn(orderIds);

        List<Order> sortedOrders = orders.stream()
                .sorted((o1, o2) -> o2.getCreatedTime().compareTo(o1.getCreatedTime()))
                .toList();

        Set<OrderDto> newOrderDtoSet = new HashSet<>();
        for (Order order : sortedOrders) {
            List<OrderDetails> orderDetails = orderDetailsList.stream()
                    .filter(orderDetails1 -> orderDetails1.getOrders().equals(order))
                    .toList();
            OrderDto orderDto = mapper.mapOrderToOrderDto(order);
            Set<OrderDetailsDto> orderDetailsDtoSet = new HashSet<>(mapper.mapOrderDetailsListToOrderDetailsDtoList(orderDetails));
            orderDto.setOrderDetails(orderDetailsDtoSet);
            newOrderDtoSet.add(orderDto);
        }
        return newOrderDtoSet;
    }

    @Override
    public OrderDto cancelOrder(Long orderId) {
        Order order = repo.getReferenceById(orderId);
        order.setOrderStatus(OrderStatus.Cancelled);
        Order updatedOrder = repo.save(order);
        return mapper.mapOrderToOrderDto(updatedOrder);
    }

    @Override
    public String deleteOrderById(Long orderId) {
        Order order = repo.getReferenceById(orderId);
        repo.deleteById(orderId);
        ODRepo.deleteAllByOrders(order);
        return "Order deleted!!!";
    }

    @Override
    public OrderDto changeOrderStatus(OrderStatus status, Long orderId) {
        Order order = repo.getReferenceById(orderId);
        order.setOrderStatus(status);
        return mapper.mapOrderToOrderDto(repo.save(order));
    }

    @Override
    public PaginatedOrderResponse getAllOrders(int page, int size) {
        List<PaginatedOrderDtoInterface> orderList = repo.getAllOrders(PageRequest.of(page - 1, size));
        List<PaginatedResponse> response = new ArrayList<>();
        for (PaginatedOrderDtoInterface p : orderList) {
            if (p.getOrderId() == p.getOdOrderId()) {
                PaginatedOrderDto dto = new PaginatedOrderDto(
                        p.getOrderId(),
                        p.getCreatedTime(),
                        p.getUpdatedTime(),
                        p.getCustomer(),
                        p.getAddressId(),
                        p.getTotal(),
                        p.getOrderStatus()
                );
                PaginatedOrderDetailDto odDto = new PaginatedOrderDetailDto(
                        p.getId(),
                        p.getInventory(),
                        p.getProductPrice(),
                        p.getProductName(),
                        p.getProductId(),
                        p.getQty(),
                        p.getOdOrderId()
                );
                PaginatedResponse pRes = new PaginatedResponse(dto, odDto);
                response.add(pRes);
            } else {
                System.out.println("odId - " + p.getOdOrderId() + " | oId - " + p.getOrderId());
            }
        }
        return new PaginatedOrderResponse(response, response.size());
    }
}