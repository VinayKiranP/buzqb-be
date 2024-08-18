package com.buz.buzqb.service.impl;

import com.buz.buzqb.dto.RoleRequest;
import com.buz.buzqb.entity.Role;
import com.buz.buzqb.repository.RoleRepo;
import com.buz.buzqb.service.RoleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepo roleRepo;

  @Autowired
  public RoleServiceImpl(RoleRepo roleRepo) {
    this.roleRepo = roleRepo;
  }

  @Override
  public List<Role> getAllRole() {
    return roleRepo.findAll();
  }

  @Override
  public Optional<Role> getRoleById(Integer id) {
    return roleRepo.findById(id);
  }

  @Override
  public Role saveRole(RoleRequest roleRequest) {
    Role role = roleRequest.requestToRole(roleRequest);
    return roleRepo.save(role);
  }

  @Override
  public Role updateRole(Role role) {
    return roleRepo.save(role);
  }
}
