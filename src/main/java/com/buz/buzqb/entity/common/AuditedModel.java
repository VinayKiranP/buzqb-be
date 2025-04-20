package com.buz.buzqb.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedModel {
  @CreatedBy
  @Column(updatable = false)
  private Long createdBy;
  @LastModifiedBy
  private Long updatedBy;
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime updatedDate;
}
