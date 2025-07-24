package com.ecomm.order_service.repository;

import com.ecomm.order_service.entity.Order;
import com.ecomm.order_service.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {

    @Query("SELECT od FROM OrderDetails od WHERE od.orders.orderId IN :orderIds")
    List<OrderDetails> findAllByOrdersIn(@Param("orderIds") List<Long> orderIds);

    void deleteAllByOrders(Order orderId);
}
