package com.ecomm.order_service.dto.paginated;

import com.ecomm.order_service.entity.enums.OrderStatus;

import java.util.Date;

public interface PaginatedOrderDtoInterface {

    Long getOrderId();

    Date getCreatedTime();

    Date getUpdatedTime();

    String getCustomer();

    Long getAddressId();

    Double getTotal();

    OrderStatus getOrderStatus();

    Double getProductPrice();

    String getProductName();

    Long getProductId();

    int getQty();
    long getId();
    int getInventory();
    long getOdOrderId();
}
