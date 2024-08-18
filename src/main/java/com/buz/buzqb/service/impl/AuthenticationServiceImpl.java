package com.buz.buzqb.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ResponseMessageUtils;
import com.buz.buzqb.dto.auth.LoginUserRequest;
import com.buz.buzqb.dto.auth.RegisterUserRequest;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.exception.InvalidValuesException;
import com.buz.buzqb.repository.BusinessRepo;
import com.buz.buzqb.service.AuthenticationService;

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
  public Business signup(RegisterUserRequest input) {
    validateRegisterRequest(input);
    Business business = new Business();
    business.setName(input.getName());
    business.setEmail(input.getEmail());
    business.setMobile(input.getMobile());
    business.setStatus(Constants.EMAIL_VERIFICATION_PENDING);
    business.setPassword(passwordEncoder.encode(input.getPassword()));
    return businessRepo.save(business);
  }

  @Override
  public Business authenticate(LoginUserRequest input) {
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

  private void validateLoginRequest(LoginUserRequest loginUserRequest) {
    InvalidValuesException exception = new InvalidValuesException();
    if (loginUserRequest != null) {
      if (loginUserRequest.getEmail() == null) {
        exception.put(Constants.EMAIL,
            ResponseMessageUtils.getFieldNotNullMessage(Constants.EMAIL));
      }

      if (loginUserRequest.getPassword() == null) {
        exception.put(Constants.PASSWORD,
            ResponseMessageUtils.getFieldNotNullMessage(Constants.PASSWORD));
      }
    } else {
      exception.put(Constants.REQUEST,
          ResponseMessageUtils.getFieldNotNullMessage(Constants.REQUEST));
    }

    if (!exception.getMessages().isEmpty()) {
      throw exception;
    }
  }

  private void validateRegisterRequest(RegisterUserRequest registerUserRequest) {
    InvalidValuesException exception = new InvalidValuesException();
    if (registerUserRequest != null) {
      if (registerUserRequest.getEmail() == null) {
        exception.put(Constants.EMAIL,
            ResponseMessageUtils.getFieldNotNullMessage(Constants.EMAIL));
      }

      if (registerUserRequest.getPassword() == null) {
        exception.put(Constants.PASSWORD,
            ResponseMessageUtils.getFieldNotNullMessage(Constants.PASSWORD));
      }

      if (registerUserRequest.getName() == null) {
        exception.put(Constants.NAME, ResponseMessageUtils.getFieldNotNullMessage(Constants.NAME));
      }

      if (registerUserRequest.getMobile().isEmpty() || registerUserRequest.getMobile() == null) {
        exception.put(Constants.MOBILE,
            ResponseMessageUtils.getFieldNotNullMessage(Constants.MOBILE));
      }

      if (!registerUserRequest.isMobileVerified()) {
        exception.put(Constants.MOBILE_VERIFICATION,
            ResponseMessageUtils.getVerificationShouldBeCompletedMessage(Constants.MOBILE));
      }
    } else {
      exception.put(Constants.REQUEST,
          ResponseMessageUtils.getFieldNotNullMessage(Constants.REQUEST));
    }

    if (!exception.getMessages().isEmpty()) {
      throw exception;
    }
  }
}
