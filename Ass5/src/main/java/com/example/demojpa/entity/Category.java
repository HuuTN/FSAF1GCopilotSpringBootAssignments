package com.example.demojpa.entity;

import com.example.demojpa.entity.base.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Self-referencing parent-child relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Category> children = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Category getParent() { return parent; }
    public void setParent(Category parent) { this.parent = parent; }
    public Set<Category> getChildren() { return children; }
    public void setChildren(Set<Category> children) { this.children = children; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id != null && id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
