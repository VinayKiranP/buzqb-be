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
  private int status;

  public Brand requestToBrand(BrandRequest brandRequest) {
    Brand brand = new Brand();
    brand.setName(brandRequest.getName());
    brand.setDescription(brandRequest.getDescription());
    brand.setStatus(brandRequest.getStatus());
    return brand;
  }
}