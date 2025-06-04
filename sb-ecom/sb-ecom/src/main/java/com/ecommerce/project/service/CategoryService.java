package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
}
