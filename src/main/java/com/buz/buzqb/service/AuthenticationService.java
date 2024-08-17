package com.buz.buzqb.service;


import com.buz.buzqb.dto.auth.LoginUserDto;
import com.buz.buzqb.dto.auth.RegisterUserDto;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.repository.BusinessRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final BusinessRepo businessRepo;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
      BusinessRepo businessRepo,
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder
  ) {
    this.authenticationManager = authenticationManager;
    this.businessRepo = businessRepo;
    this.passwordEncoder = passwordEncoder;
  }

  public Business signup(RegisterUserDto input) {
    Business business = new Business();
    business.setName(input.getName());
    business.setEmail(input.getEmail());
    business.setPassword(passwordEncoder.encode(input.getPassword()));
    return businessRepo.save(business);
  }

  public Business authenticate(LoginUserDto input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.getEmail(),
            input.getPassword()
        )
    );

    return businessRepo.findByEmail(input.getEmail())
        .orElseThrow();
  }
}