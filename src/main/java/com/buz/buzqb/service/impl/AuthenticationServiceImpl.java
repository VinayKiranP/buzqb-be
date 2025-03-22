package com.buz.buzqb.service.impl;

import java.util.Optional;
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

  /**
   * Sign In
   * @param registerUserRequest
   * @return
   */
  @Override
  public Business signup(RegisterUserRequest registerUserRequest) {
    validateRegisterRequest(registerUserRequest);
    checkForDuplicateRecord(registerUserRequest);
    Business business = new Business();
    business.setName(registerUserRequest.getName());
    business.setEmail(registerUserRequest.getEmail());
    business.setMobile(registerUserRequest.getMobile());
    business.setStatus(Constants.EMAIL_VERIFICATION_PENDING);
    business.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
    business.setRoleId(Constants.TWO);
    return businessRepo.save(business);
  }

  /**
   * Check Duplicate Business Record By Email
   * @param input
   */
  private void checkForDuplicateRecord(RegisterUserRequest input) {
    Optional<Business> isExist = businessRepo.findByEmail(input.getEmail());
    if (isExist.isPresent()){
      InvalidValuesException exception = new InvalidValuesException();
      exception.put(Constants.REQUEST,
          ResponseMessageUtils.getDuplicateRecordMessage(Constants.BUSINESS, Constants.EMAIL));
      throw exception;
    }
  }

  /**
   * Authenticate Business User
   * @param input
   * @return
   */
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

  /**
   * Validate Login Request
   * @param loginUserRequest
   */
  private void validateLoginRequest(LoginUserRequest loginUserRequest) {
    InvalidValuesException exception = new InvalidValuesException();
    if (loginUserRequest != null) {
      if (loginUserRequest.getEmail() == null || loginUserRequest.getEmail().isEmpty()) {
        exception.put(Constants.EMAIL,
            ResponseMessageUtils.getFieldNotNullMessage(Constants.EMAIL));
      }

      if (loginUserRequest.getPassword() == null || loginUserRequest.getPassword().isEmpty()) {
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

  /**
   * Validate Business Registration Request
   * @param registerUserRequest
   */
  private void validateRegisterRequest(RegisterUserRequest registerUserRequest) {
    InvalidValuesException exception = new InvalidValuesException();
    if (registerUserRequest != null) {
      validateRegisterRequestFields(registerUserRequest, exception);
    } else {
      exception.put(Constants.REQUEST,
          ResponseMessageUtils.getFieldNotNullMessage(Constants.REQUEST));
    }

    if (!exception.getMessages().isEmpty()) {
      throw exception;
    }
  }

  private static void validateRegisterRequestFields(RegisterUserRequest registerUserRequest,
      InvalidValuesException exception) {
    if (registerUserRequest.getEmail() == null || registerUserRequest.getEmail().isEmpty()) {
      exception.put(Constants.EMAIL,
          ResponseMessageUtils.getFieldNotNullMessage(Constants.EMAIL));
    }
    if (registerUserRequest.getPassword().isEmpty() || registerUserRequest.getPassword() == null) {
      exception.put(Constants.PASSWORD,
          ResponseMessageUtils.getFieldNotNullMessage(Constants.PASSWORD));
    }
    if (registerUserRequest.getName().isEmpty() || registerUserRequest.getName() == null) {
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
  }
}
