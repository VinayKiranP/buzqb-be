package com.buz.buzqb.service;

import com.buz.buzqb.dto.RoleRequest;
import com.buz.buzqb.entity.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

  List<Role> getAllRole();

  Optional<Role> getRoleById(Long id);

  Role saveRole(Role role);

  Role updateRole(Role role);
}
