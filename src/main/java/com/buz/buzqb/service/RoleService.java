package com.buz.buzqb.service;

import com.buz.buzqb.dto.RoleRequest;
import com.buz.buzqb.entity.Role;
import com.buz.buzqb.repository.RoleRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public List<Role> getAllRole() {
        return roleRepo.findAll();
    }

    public Optional<Role> getRoleById(Integer id){
        return roleRepo.findById(id);
    }

    public Role saveRole(RoleRequest roleRequest){
        Role role = roleRequest.requestToRole(roleRequest);
        return roleRepo.save(role);
    }

    public Role updateRole(Role role){
        return roleRepo.save(role);
    }
}
