package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Post;
import java.awt.TextField;
import lombok.Data;

@Data
public class PostRequest {

  private String title;
  private TextField description;
  private String topic;
  private Long businessId;
  private String imageUrl;
  private int status;

  public Post requestToPost(PostRequest postRequest) {
    Post post = new Post();
    post.setTitle(postRequest.getTitle());
    post.setDescription(postRequest.getDescription());
    post.setTopic(postRequest.getTopic());
    post.setBusinessId(postRequest.getBusinessId());
    post.setImageUrl(postRequest.getImageUrl());
    post.setStatus(postRequest.getStatus());
    return post;
  }
}
