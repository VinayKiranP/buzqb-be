package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Permission;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class PermissionRequest {

  @NonNull
  private String name;
  @NonNull
  @Column(length = 5)
  private String code;
  @NonNull
  private int status;
  @NonNull
  private Long businessId;
  @NonNull
  private Long roleId;

    public Permission requestToPermission(PermissionRequest permissionRequest) {
    Permission permission = new Permission();
    permission.setName(permissionRequest.getName());
    permission.setCode(permissionRequest.getCode());
    permission.setStatus(permissionRequest.getStatus());
    permission.setBusinessId(permissionRequest.getBusinessId());
    permission.setRoleId(permissionRequest.getRoleId());
    return permission;
  }
}
