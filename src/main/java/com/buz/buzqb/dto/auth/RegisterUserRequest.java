package com.buz.buzqb.dto.auth;

import lombok.Data;

@Data
public class RegisterUserRequest {
  private String email;
  private String password;
  private String name;
  private String mobile;
  private boolean mobileVerified;
}
