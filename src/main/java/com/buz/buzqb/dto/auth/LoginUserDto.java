package com.buz.buzqb.dto.auth;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class LoginUserDto {
  @NotNull
  private String email;
  @NotNull
  private String password;
}
