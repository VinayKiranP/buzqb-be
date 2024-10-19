package com.buz.buzqb.entity.view;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Table(name = "view_business")
@Immutable
@Data
@MappedSuperclass
public class BusinessView implements Serializable {

  @Id
  protected Integer id;
  private String name;
  private String email;
  private String status;
}
