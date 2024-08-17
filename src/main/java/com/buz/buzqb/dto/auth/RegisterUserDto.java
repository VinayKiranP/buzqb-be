package com.buz.buzqb.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDto {
  @NotNull
  private String email;
  @NotNull
  private String password;
  @NotNull
  private String name;
}
