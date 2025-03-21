package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Category;
import com.buz.buzqb.repository.CategoryRepo;
import com.buz.buzqb.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepo categoryRepo;

  @Autowired
  public CategoryServiceImpl(CategoryRepo categoryRepo) {
    this.categoryRepo = categoryRepo;
  }

  @Override
  public List<Category> getAllCategory() {
    return categoryRepo.findAll();
  }

  @Override
  @Cacheable(value = "category")
  public Optional<Category> getCategoryById(Long id) {
    var data = categoryRepo.findById(id);
    var entity = data.map(category -> Hibernate.unproxy(category, Category.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "category", key = "#category.id")
  public Category saveCategory(Category category) {
    return categoryRepo.save(category);
  }

  @Override
  @CacheEvict(value = "category", key = "#category.id")
  public Category updateCategory(Category category) {
    return categoryRepo.save(category);
  }
}
