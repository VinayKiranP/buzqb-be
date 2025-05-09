package com.buz.buzqb.entity;

import com.buz.buzqb.entity.common.AuditedModel;
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
@Entity(name = "sale")
public class Sales extends AuditedModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String number;
  private String description;
  private Long itemId;
  private Long stockId;
  private Double quantity;
  private Double price;
  private Double discount;
  private Double tax;
  private Double total;
  private Long businessId;
  private int paymentType; // 1: Upi, 2: Card, 3: Cash, 4: Other
  private int saleType; // 1: CarryIn, 2: Online, 4: Other
  private Long customerId;
  private Long invoiceId;
  private int status;
}