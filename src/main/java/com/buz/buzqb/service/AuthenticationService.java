package com.buz.buzqb.service;

import com.buz.buzqb.dto.auth.LoginUserDto;
import com.buz.buzqb.dto.auth.RegisterUserDto;
import com.buz.buzqb.entity.Business;

public interface AuthenticationService {

  Business signup(RegisterUserDto input);

  Business authenticate(LoginUserDto input);
}