package com.ecomm.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String categoryName;

    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(Long category) {
        this.categoryId = category;
    }

    public Category(Long categoryId, String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

}
