package com.buz.buzqb.dto.auth;


public class LoginResponse {

  private String token;
  private long expiresIn;

  public void setToken(String token) {
    this.token = token;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getToken() {
    return token;
  }
}
