package com.buz.buzqb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "business")
public class Business implements UserDetails, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;
  private String name;
  private String about;
  private String mobile;
  @Column(unique = true, length = 250, nullable = false)
  private String email;
  @JsonIgnore
  private String password;
  @JsonIgnore
  private String username;
  @Column(name = "address_line1")
  private String addressLine1;
  @Column(name = "address_line2")
  private String addressLine2;
  private String landmark;
  private String city;
  private Integer stateId;
  private Integer countryId;
  private String pinCode;
  private String gst;
  // profile pic check fields
  private String profilePic;
  private int profilePicVisibility;
  private String commentType;
  private String comment;
  // user provider check fields and status
  private String providerUserId;
  private String provider;
  private Integer roleId;
  @NotNull
  private int priority; // 0-admin, 1-owner, 2-manager, 3-biller, 4-storekeeper, 5-operator
  @NotNull
  private Long businessId;
  private Boolean emailVerified;
  private String status; // 0-inactive, 1-active, 2-suspended, 3-deleted

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}