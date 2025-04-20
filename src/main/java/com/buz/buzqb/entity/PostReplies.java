package com.buz.buzqb.entity;

import com.buz.buzqb.entity.common.AuditedModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.awt.TextField;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post_replies")
public class PostReplies extends AuditedModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long postId;
  private String title;
  private TextField description;
  private Long businessId;
  private String imageUrl;
  private int status;
}