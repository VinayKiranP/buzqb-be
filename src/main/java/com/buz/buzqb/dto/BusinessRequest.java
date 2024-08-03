package com.buz.buzqb.dto;

import com.buz.buzqb.model.Business;

import lombok.*;

@Data
public class BusinessRequest {
    private String name;
    private String username;
    private String pincode;
    private String status;

    public Business requestToBusiness(BusinessRequest businessRequest){
        Business business = new Business();
        business.setName(businessRequest.getName());
        business.setUsername(businessRequest.getUsername());
        business.setPincode(businessRequest.getPincode());
        business.setStatus(businessRequest.getStatus());
        return business;
    }
}
