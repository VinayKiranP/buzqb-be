package com.buz.buzqb.service;

import com.buz.buzqb.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

  List<Category> getAllCategory();

  Optional<Category> getCategoryById(Long id);

  Category saveCategory(Category category);

  Category updateCategory(Category category);
}
