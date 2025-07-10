package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.example.demo.entity.base.Auditable;

@Entity
@Table(name = "products")
@Data
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
