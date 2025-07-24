package com.ecomm.order_service.entity;

import com.ecomm.order_service.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Date createdTime;

    private Date updatedTime;

    private String customer;

    private Long addressId;

    private Double total;


    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
    private Set<OrderDetails> orderDetails;

    public Order(Long orderId, Date createdTime, Date updatedTime, String customer, Long addressId, Double total, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.customer = customer;
        this.addressId = addressId;
        this.total = total;
        this.orderStatus = orderStatus;
    }
}
