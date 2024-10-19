package com.buz.buzqb.entity.common;

import com.buz.buzqb.entity.view.BusinessView;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Data
@MappedSuperclass
public class AuditModel implements Serializable {

  @Audited
  @Column(name = "created_date_time", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Restricted
  protected Date createdDateTime;

  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "created_by")
  @Restricted
  @JsonProperty
  protected BusinessView createdBy;

  @Audited
  @Column(name = "updated_date_time", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  protected Date updatedDateTime;

  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "updated_by")
  @JsonProperty
  protected BusinessView updatedBy;

}
