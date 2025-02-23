package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Brand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrandRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  private String description;
  private String status;

  public Brand requestToBrand(BrandRequest brandRequest) {
    Brand role = new Brand();
    role.setName(brandRequest.getName());
    role.setDescription(brandRequest.getDescription());
    role.setStatus(brandRequest.getStatus());
    return role;
  }
}