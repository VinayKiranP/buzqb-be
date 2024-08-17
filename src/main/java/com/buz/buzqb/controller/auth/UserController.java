package com.buz.buzqb.controller.auth;

import com.buz.buzqb.entity.Business;
import com.buz.buzqb.service.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
  private final BusinessService businessService;

  public UserController(BusinessService businessService) {
    this.businessService = businessService;
  }

  @GetMapping("/me")
  public ResponseEntity<Business> authenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Business currentUser = (Business) authentication.getPrincipal();

    return ResponseEntity.ok(currentUser);
  }

  @GetMapping("/")
  public ResponseEntity<List<Business>> allBusiness() {
    List <Business> users = businessService.getAllBusiness();

    return ResponseEntity.ok(users);
  }
}