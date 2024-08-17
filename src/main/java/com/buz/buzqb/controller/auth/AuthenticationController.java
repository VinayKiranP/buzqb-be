package com.buz.buzqb.controller.auth;

import com.buz.buzqb.auth.JwtService;
import com.buz.buzqb.common.Constants;
import com.buz.buzqb.dto.auth.LoginResponse;
import com.buz.buzqb.dto.auth.LoginUserDto;
import com.buz.buzqb.dto.auth.RegisterUserDto;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Constants.AUTH_URI)
@RestController
public class AuthenticationController {
  private final JwtService jwtService;

  private final AuthenticationService authenticationService;

  public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
  }

  @PostMapping(Constants.SIGNUP_URI)
  public ResponseEntity<Business> register(@RequestBody RegisterUserDto registerUserDto) {
    Business registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping(Constants.LOGIN_URI)
  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    Business authenticatedUser = authenticationService.authenticate(loginUserDto);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);
  }
}