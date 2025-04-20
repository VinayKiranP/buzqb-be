package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Permission;
import com.buz.buzqb.repository.PermissionRepo;
import com.buz.buzqb.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {

  private final PermissionRepo permissionRepo;

  @Autowired
  public PermissionServiceImpl(PermissionRepo permissionRepo) {
    this.permissionRepo = permissionRepo;
  }

  @Override
  public List<Permission> getAllPermission() {
    return permissionRepo.findAll();
  }

  @Override
  @Cacheable(value = "permission")
  public Optional<Permission> getPermissionById(Long id) {
    var data = permissionRepo.findById(id);
    var entity = data.map(permission -> Hibernate.unproxy(permission, Permission.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "permission", key = "#permission.id")
  public Permission savePermission(Permission permission) {
    return permissionRepo.save(permission);
  }

  @Override
  @CacheEvict(value = "permission", key = "#permission.id")
  public Permission updatePermission(Permission permission) {
    return permissionRepo.save(permission);
  }
}
