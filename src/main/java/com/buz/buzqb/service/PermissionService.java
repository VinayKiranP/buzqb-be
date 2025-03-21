package com.buz.buzqb.service;

import com.buz.buzqb.entity.Permission;
import java.util.List;
import java.util.Optional;

public interface PermissionService {

  List<Permission> getAllPermission();

  Optional<Permission> getPermissionById(Long id);

  Permission savePermission(Permission permission);

  Permission updatePermission(Permission permission);
}
