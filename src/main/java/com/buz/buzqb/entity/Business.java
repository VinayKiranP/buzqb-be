package com.buz.buzqb.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private String mobile;
    private String email;
    private String password;
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
    private String status;
}