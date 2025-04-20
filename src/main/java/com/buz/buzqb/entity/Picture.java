package com.buz.buzqb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "picture")
public class Picture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String code;
  private String name;
  private String description;
  private String type;
  private byte[] imageData;
  private Long businessId;
  private int status = 1;
  private String comment;
}