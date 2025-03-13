package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  private String description;
  private String status;

  public Category requestToCategory(CategoryRequest categoryRequest) {
    Category category = new Category();
    category.setName(categoryRequest.getName());
    category.setDescription(categoryRequest.getDescription());
    category.setStatus(categoryRequest.getStatus());
    return category;
  }
}