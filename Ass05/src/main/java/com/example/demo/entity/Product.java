package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.example.demo.entity.base.Auditable;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Product name is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
