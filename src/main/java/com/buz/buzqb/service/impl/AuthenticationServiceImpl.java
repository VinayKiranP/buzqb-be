package com.buz.buzqb.service.impl;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ResponseMessageUtils;
import com.buz.buzqb.dto.auth.LoginUserDto;
import com.buz.buzqb.dto.auth.RegisterUserDto;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.exception.InvalidValuesException;
import com.buz.buzqb.repository.BusinessRepo;
import com.buz.buzqb.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private final BusinessRepo businessRepo;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public AuthenticationServiceImpl(
      BusinessRepo businessRepo,
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder
  ) {
    this.authenticationManager = authenticationManager;
    this.businessRepo = businessRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Business signup(RegisterUserDto input) {
    validateRegisterRequest(input);
    Business business = new Business();
    business.setName(input.getName());
    business.setEmail(input.getEmail());
    business.setPassword(passwordEncoder.encode(input.getPassword()));
    return businessRepo.save(business);
  }

  @Override
  public Business authenticate(LoginUserDto input) {
    validateLoginRequest(input);
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.getEmail(),
            input.getPassword()
        )
    );

    return businessRepo.findByEmail(input.getEmail())
        .orElseThrow();
  }

  private void validateLoginRequest(LoginUserDto loginUserDto) {
    InvalidValuesException exception = new InvalidValuesException();
    if (loginUserDto != null) {
      if (loginUserDto.getEmail() == null) {
        exception.put(Constants.EMAIL, ResponseMessageUtils.getFieldNotNullMessage(Constants.EMAIL));
      }

      if (loginUserDto.getPassword() == null) {
        exception.put(Constants.PASSWORD, ResponseMessageUtils.getFieldNotNullMessage(Constants.PASSWORD));
      }
    } else {
      exception.put(Constants.REQUEST, ResponseMessageUtils.getFieldNotNullMessage(Constants.REQUEST));
    }

    if (!exception.getMessages().isEmpty()) {
      throw exception;
    }
  }

  private void validateRegisterRequest(RegisterUserDto registerUserDto) {
    InvalidValuesException exception = new InvalidValuesException();
    if (registerUserDto != null) {
      if (registerUserDto.getEmail() == null) {
        exception.put(Constants.EMAIL, ResponseMessageUtils.getFieldNotNullMessage(Constants.EMAIL));
      }

      if (registerUserDto.getPassword() == null) {
        exception.put(Constants.PASSWORD, ResponseMessageUtils.getFieldNotNullMessage(Constants.PASSWORD));
      }

      if (registerUserDto.getName() == null) {
        exception.put(Constants.NAME, ResponseMessageUtils.getFieldNotNullMessage(Constants.NAME));
      }
    } else {
      exception.put(Constants.REQUEST, ResponseMessageUtils.getFieldNotNullMessage(Constants.REQUEST));
    }

    if (!exception.getMessages().isEmpty()) {
      throw exception;
    }
  }
}
