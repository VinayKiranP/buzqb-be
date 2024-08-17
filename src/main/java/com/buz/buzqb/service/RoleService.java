package com.buz.buzqb.service;

import com.buz.buzqb.dto.RoleRequest;
import com.buz.buzqb.entity.Role;
import com.buz.buzqb.repository.RoleRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface RoleService {

  List<Role> getAllRole();

  Optional<Role> getRoleById(Integer id);

  Role saveRole(RoleRequest roleRequest);

  Role updateRole(Role role);
}
