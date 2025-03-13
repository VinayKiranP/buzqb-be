package com.buz.buzqb.dto;

import com.buz.buzqb.entity.SubCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SubCategoryRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  private String description;
  private Long categoryId;
  private String status;

  public SubCategory requestToSubCategory(SubCategoryRequest subCategoryRequest) {
    SubCategory subCategory = new SubCategory();
    subCategory.setName(subCategoryRequest.getName());
    subCategory.setCategoryId(subCategoryRequest.getCategoryId());
    subCategory.setDescription(subCategoryRequest.getDescription());
    subCategory.setStatus(subCategoryRequest.getStatus());
    return subCategory;
  }
}