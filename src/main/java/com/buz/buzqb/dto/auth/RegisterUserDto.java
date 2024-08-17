package com.buz.buzqb.dto.auth;

import lombok.Data;

@Data
public class RegisterUserDto {
  private String email;
  private String password;
  private String name;
}
