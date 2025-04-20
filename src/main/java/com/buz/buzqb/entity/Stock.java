package com.buz.buzqb.entity;

import com.buz.buzqb.entity.common.AuditedModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stock")
public class Stock extends AuditedModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String code;
  private String modelNumber;
  private String description;
  private Long available;
  private Long forReserve;
  private Double mrp;
  private Double purchasedPrice;
  private Double sellingPrice;
  private Double tax;
  private int status = 1;
  private Long businessId;
}