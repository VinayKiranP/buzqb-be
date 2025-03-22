package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Role;
import com.buz.buzqb.repository.RoleRepo;
import com.buz.buzqb.service.RoleService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
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
  @Cacheable(value = "role")
  public Optional<Role> getRoleById(Long id) {
    var data = roleRepo.findById(id);
    var entity = data.map(role -> Hibernate.unproxy(role, Role.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "role", key = "#role.id")
  public Role saveRole(Role role) {
    return roleRepo.save(role);
  }

  @Override
  @CacheEvict(value = "role", key = "#role.id")
  public Role updateRole(Role role) {
    return roleRepo.save(role);
  }

  @Override
  public List<Role> getAllRoleForBusiness(Long roleId) {
    return roleRepo.findByPriorityNotIn(roleId);
  }
}
