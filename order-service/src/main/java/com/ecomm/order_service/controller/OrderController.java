package com.ecomm.order_service.controller;

import com.ecomm.order_service.dto.OrderDto;
import com.ecomm.order_service.dto.paginated.PaginatedOrderResponse;
import com.ecomm.order_service.entity.enums.OrderStatus;
import com.ecomm.order_service.exceptions.ProductNotFoundException;
import com.ecomm.order_service.service.OrderService;
import com.ecomm.order_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    private String successMsg = "Success";

    @PostMapping("/place-order") // tested, works
    public ResponseEntity<StandardResponse> makeOrder(@RequestBody OrderDto orderDto) throws ProductNotFoundException {
        OrderDto savedOrder = orderService.saveOrder(orderDto);
        return new ResponseEntity<>(
                new StandardResponse(200, successMsg, savedOrder),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-orders-by-status/{status}") // tested, works
    public ResponseEntity<StandardResponse> getAllOrdersByStatus(@PathVariable OrderStatus status){
        Set<OrderDto> orderDtos = orderService.getAllOrdersByStatus(status);
        return new ResponseEntity<>(
                new StandardResponse(200,successMsg, orderDtos),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-orders-with-customer-and-status/{cusId}/{status}") // not tested
    public ResponseEntity<StandardResponse> getOrdersWithCustomerAndStatus(@PathVariable("cusId") String cusId,
                                                                           @PathVariable("status") OrderStatus status){
        Set<OrderDto> orderDtos = orderService.getOrdersWithCusAndStatus(cusId, status);
        return new ResponseEntity<>(
                new StandardResponse(200, successMsg, orderDtos),
                HttpStatus.OK
        );
    }

    @PutMapping("/cancel-order/{orderId}") // not tested
    public ResponseEntity<StandardResponse> cancelOrder(Long orderId){
        OrderDto orderDto = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(
                new StandardResponse(200, successMsg, orderDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-order-by-id") // not tested
    public ResponseEntity<StandardResponse> deleteOrderById(@Param("orderId") Long orderId){
        String msg = orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(
                new StandardResponse(200, "Order Deleted", msg),
                HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/change-order-status/{status}&&{orderId}") // not tested
    public ResponseEntity<StandardResponse> changeOrderStatus(@PathVariable("status") OrderStatus status,
                                                              @PathVariable("orderId") Long orderId){
        OrderDto orderDto = orderService.changeOrderStatus(status, orderId);
        return new ResponseEntity<>(
                new StandardResponse(200, successMsg, orderDto),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-orders-by-customer-id/{cusId}") // not tested
    public ResponseEntity<StandardResponse> getOrdersByCusId(@PathVariable String cusId){
        Set<OrderDto> orderDtos = orderService.getOrdersByCusId(cusId);
        return new ResponseEntity<>(
                new StandardResponse(200, successMsg, orderDtos),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/get-all-orders", params = {"page", "size"}) // not tested
    public ResponseEntity<StandardResponse> getAllOrders(@RequestParam int page,
                                                         @RequestParam int size){
        PaginatedOrderResponse response = orderService.getAllOrders(page, size);
        return new ResponseEntity<>(
                new StandardResponse(200, successMsg, response),
                HttpStatus.OK
        );
    }
}