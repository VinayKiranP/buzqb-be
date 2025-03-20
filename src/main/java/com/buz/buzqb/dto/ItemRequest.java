package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Item;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  @NotNull
  @Size(max = 50, message = "code can't exceed 50 characters")
  private String code;
  private String description;
  private Long categoryId;
  private Long subCategoryId;
  private Long brandId;
  private int status;

  public Item requestToItem(ItemRequest itemRequest) {
    Item item = new Item();
    item.setName(itemRequest.getName());
    item.setCode(itemRequest.getCode());
    item.setCategoryId(itemRequest.getCategoryId());
    item.setSubCategoryId(itemRequest.getSubCategoryId());
    item.setBrandId(itemRequest.getBrandId());
    item.setDescription(itemRequest.getDescription());
    item.setStatus(itemRequest.getStatus());
    return item;
  }
}