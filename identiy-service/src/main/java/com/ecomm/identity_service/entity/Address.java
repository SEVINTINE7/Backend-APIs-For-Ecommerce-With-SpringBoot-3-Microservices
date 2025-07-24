package com.ecomm.identity_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(nullable = false)
    private String addressNo;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String country;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
