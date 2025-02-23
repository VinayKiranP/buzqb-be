package com.buz.buzqb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "business")
public class Business implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;
  private String name;
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
  @Column(name = "state_id")
  private Integer stateId;
  @Column(name = "country_id")
  private Integer countryId;
  private String pincode;
  @Column(name = "email_verified")
  private boolean emailVerified;
  @Column(name = "profile_pic")
  private String profilePic;
  @Column(name = "provider_user_id")
  private String providerUserId;
  private String about;
  private String provider;
  private String status;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

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