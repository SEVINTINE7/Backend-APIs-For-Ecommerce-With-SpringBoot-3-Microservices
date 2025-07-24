package com.ecomm.order_service.util.mappers;

import com.ecomm.order_service.dto.OrderDetailsDto;
import com.ecomm.order_service.dto.OrderDto;
import com.ecomm.order_service.entity.Order;
import com.ecomm.order_service.entity.OrderDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class Mapper {

    public Order mapOrderDtoToOrder(OrderDto orderDto){
        return new Order(
                orderDto.getOrderId(),
                orderDto.getCreatedTime(),
                orderDto.getUpdatedTime(),
                orderDto.getCustomer(),
                orderDto.getAddressId(),
                orderDto.getTotal(),
                orderDto.getOrderStatus(),
                null
        );
    }
//    public OrderDetailsDto mapOrderDetailToOrderDetailDto(OrderDetails od, Long orderId){
//        return new OrderDetailsDto(
//                od.getDetailsId(),
//                od.getQty(),
//                od.getProductId(),
//                od.getProductName(),
//                od.getProductPrice(),
//                od.getInventory(),
//                orderId
//        );
//    }

    public OrderDto mapOrderToOrderDto(Order o){
        List<OrderDetails> orderDetailsSet = new ArrayList<>(o.getOrderDetails());
        Set<OrderDetailsDto> orderDetailsDtos = new HashSet<>(mapOrderDetailsListToOrderDetailsDtoList(orderDetailsSet));
        return new OrderDto(
                o.getOrderId(),
                o.getCreatedTime(),
                o.getUpdatedTime(),
                o.getCustomer(),
                o.getAddressId(),
                o.getTotal(),
                o.getOrderStatus(),
                orderDetailsDtos
        );
    }

    public OrderDetails mapOrderDetailsDtoToOrderDetailsSp(OrderDetailsDto dto, Order order){
        return new OrderDetails(
                dto.getId(),
                dto.getQty(),
                dto.getProductId(),
                dto.getProductName(),
                dto.getProductPrice(),
                dto.getInventory(),
                order
        );
    }

    public OrderDetailsDto mapOrderDetailsToOrderDetailsDto(OrderDetails o){
        return new OrderDetailsDto(
                o.getDetailsId(),
                o.getQty(),
                o.getProductId(),
                o.getProductName(),
                o.getProductPrice(),
                o.getInventory(),
                o.getOrders().getOrderId()
        );
    }

    public List<OrderDetailsDto> mapOrderDetailsListToOrderDetailsDtoList(List<OrderDetails> odList){
        List<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();
        for (OrderDetails o: odList){
            OrderDetailsDto dto = mapOrderDetailsToOrderDetailsDto(o);
            orderDetailsDtos.add(dto);
        }
        return orderDetailsDtos;
    }

    public Set<OrderDto> mapOrderSetToOrderDtoSet(Set<Order> orders){
        Set<OrderDto> orderDtos = new HashSet<>();
        for (Order o: orders){
            OrderDto dto = mapOrderToOrderDto(o);
            orderDtos.add(dto);
        }
        return orderDtos;
    }
}
