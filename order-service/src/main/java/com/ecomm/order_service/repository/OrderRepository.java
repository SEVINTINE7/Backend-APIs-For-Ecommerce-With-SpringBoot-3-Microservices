package com.ecomm.order_service.repository;

import com.ecomm.order_service.dto.paginated.PaginatedOrderDtoInterface;
import com.ecomm.order_service.entity.Order;
import com.ecomm.order_service.entity.enums.OrderStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> findAllByCustomer(@Param("customer") String customer);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = :status")
    List<Order> findAllByOrderStatus(@Param("status") OrderStatus status);

    @Query("SELECT o FROM Order o WHERE o.customer = :cusId AND o.orderStatus = :status")
    List<Order> findAllByCustomerAndStatus(@Param("cusId") String cusId,@Param("status") int status);

    @Query(value = "SELECT o.order_id as orderId, o.created_time as createdTime, o.updated_time as updatedTime, o.customer as customer," +
            "o.address_id as addressId, o.total as total, o.order_status as orderStatus, od.qty as qty, od.product_id as productId," +
            "od.product_name as productName, od.product_price as productPrice, od.detail_id as id, od.inventory as inventory, od.order_id as odOrderId" +
            " FROM order o INNER JOIN order_details od ON od.order_id = o.order_id", nativeQuery = true)
    List<PaginatedOrderDtoInterface> getAllOrders(PageRequest of);
}
