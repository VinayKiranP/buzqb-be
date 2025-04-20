package com.buz.buzqb.entity;

import com.buz.buzqb.entity.common.AuditedModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stock_movement")
public class StockMovement extends AuditedModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String code;
  private String modelNumber;
  private String description;
  private Long available;
  private Long forReserve;
  private Double mrp;
  private Double purchasedPrice;
  private Double sellingPrice;
  private int status = 0;
  private Long businessId;
  private Long approvedBy;
  private LocalDateTime approvedDate;
}