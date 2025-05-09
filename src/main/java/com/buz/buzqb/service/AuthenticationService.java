package com.buz.buzqb.service;

import com.buz.buzqb.dto.auth.LoginUserRequest;
import com.buz.buzqb.dto.auth.RegisterUserRequest;
import com.buz.buzqb.dto.auth.ResetUserPasswordRequest;
import com.buz.buzqb.entity.Business;

public interface AuthenticationService {

  Business signup(RegisterUserRequest input);

  Business authenticate(LoginUserRequest input);

  Business resetPassword(ResetUserPasswordRequest resetUserPasswordRequest);
}