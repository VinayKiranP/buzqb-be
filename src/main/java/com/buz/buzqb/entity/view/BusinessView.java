package com.buz.buzqb.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

//@Entity
//@Table(name = "view_business")
@Immutable
@Data
public class BusinessView implements Serializable {

  @Id
  protected Integer id;
  private String name;
  private String email;
  @Column(name = "address_line1")
  private String addressLine1;
  @Column(name = "address_line2")
  private String addressLine2;
  private String landmark;
  private String city;
  private String pincode;
  private String status;
}
