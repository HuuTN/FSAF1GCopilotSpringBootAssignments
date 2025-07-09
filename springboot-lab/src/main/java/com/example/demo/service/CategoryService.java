package com.example.demo.service;

import com.example.demo.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findRootCategories();
}
