package com.buz.buzqb.dto.auth;

import lombok.Data;

@Data
public class ResetUserPasswordRequest {

  private String email;
  private String password;
}
