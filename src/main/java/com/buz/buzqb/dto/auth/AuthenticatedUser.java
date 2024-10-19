package com.buz.buzqb.dto.auth;

import com.buz.buzqb.entity.Business;
import java.io.Serializable;
import lombok.Data;

@Data
public class AuthenticatedUser implements Serializable {

  private Integer id;
  private String email;
  private String name;
  private String status;

  public AuthenticatedUser(Integer id, String email, String name, String status) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.status = status;
  }

  public AuthenticatedUser(Integer id) {
    this.id = id;
  }

  public AuthenticatedUser(Business business) {
    this.id = business.getId();
    this.email = business.getEmail();
    this.name = business.getName();
    this.status = business.getStatus();
  }
}
