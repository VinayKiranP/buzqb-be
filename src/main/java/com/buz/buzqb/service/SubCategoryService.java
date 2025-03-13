package com.buz.buzqb.service;

import com.buz.buzqb.entity.SubCategory;
import java.util.List;
import java.util.Optional;

public interface SubCategoryService {

  List<SubCategory> getAllSubCategory();

  Optional<SubCategory> getSubCategoryById(Long id);

  SubCategory saveSubCategory(SubCategory subCategory);

  SubCategory updateSubCategory(SubCategory subCategory);
}
