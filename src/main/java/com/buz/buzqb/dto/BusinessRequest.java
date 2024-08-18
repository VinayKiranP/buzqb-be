package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Business;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BusinessRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  @NotNull
  @Size(max = 10, message = "mobile can't exceed 250 characters")
  private String mobile;
  @NotNull
  @Size(max = 250, message = "username can't exceed 250 characters")
  private String email;
  @Size(min = 8, max = 124, message = "password should be between 8-124 characters")
  private String password;
  private String addressLine1;
  private String addressLine2;
  private String landmark;
  private String city;
  private Integer stateId;
  private Integer countryId;
  @Size(max = 6, message = "postcode can't exceed 6 characters")
  private String pincode;
  private String status;

  public Business requestToBusiness(BusinessRequest businessRequest) {
    Business business = new Business();
    business.setName(businessRequest.getName());
    business.setMobile(businessRequest.getMobile());
    business.setEmail(businessRequest.getEmail());
//        business.setPassword(businessRequest.getPassword());
    business.setAddressLine1(businessRequest.getAddressLine1());
    business.setAddressLine2(businessRequest.getAddressLine2());
    business.setLandmark(businessRequest.getLandmark());
    business.setCity(businessRequest.getCity());
    business.setPincode(businessRequest.getPincode());
    business.setStateId(businessRequest.getStateId());
    business.setCountryId(businessRequest.getCountryId());
    business.setPincode(businessRequest.getPincode());
    business.setStatus(businessRequest.getStatus());
    return business;
  }
}
