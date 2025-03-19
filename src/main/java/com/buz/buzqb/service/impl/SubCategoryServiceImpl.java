package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.SubCategory;
import com.buz.buzqb.repository.SubCategoryRepo;
import com.buz.buzqb.service.SubCategoryService;
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
public class SubCategoryServiceImpl implements SubCategoryService {

  private final SubCategoryRepo subCategoryRepo;

  @Autowired
  public SubCategoryServiceImpl(SubCategoryRepo subCategoryRepo) {
    this.subCategoryRepo = subCategoryRepo;
  }

  @Override
  public List<SubCategory> getAllSubCategory() {
    return subCategoryRepo.findAll();
  }

  @Override
  @Cacheable(value = "subCategory")
  public Optional<SubCategory> getSubCategoryById(Long id) {
    var data = subCategoryRepo.findById(id);
    var entity = data.map(subCategory -> Hibernate.unproxy(subCategory, SubCategory.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "subCategory", key = "#subCategory.id")
  public SubCategory saveSubCategory(SubCategory subCategory) {
    return subCategoryRepo.save(subCategory);
  }

  @Override
  @CacheEvict(value = "subCategory", key = "#subCategory.id")
  public SubCategory updateSubCategory(SubCategory subCategory) {
    return subCategoryRepo.save(subCategory);
  }
}
