package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  private String description;
  private String status;

  public Role requestToRole(RoleRequest roleRequest) {
    Role role = new Role();
    role.setName(roleRequest.getName());
    role.setDescription(roleRequest.getDescription());
    role.setStatus(roleRequest.getStatus());
    return role;
  }
}
