package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Post;
import com.buz.buzqb.entity.PostReplies;
import java.awt.TextField;
import lombok.Data;

@Data
public class PostRepliesRequest {

  private Long postId;
  private String title;
  private TextField description;
  private Long businessId;
  private String imageUrl;
  private int status;

  public PostReplies requestToPostReplies(PostRepliesRequest postRepliesRequest) {
    PostReplies postReplies = new PostReplies();
    postReplies.setPostId(postRepliesRequest.postId);
    postReplies.setTitle(postRepliesRequest.title);
    postReplies.setDescription(postRepliesRequest.description);
    postReplies.setBusinessId(postRepliesRequest.businessId);
    postReplies.setImageUrl(postRepliesRequest.imageUrl);
    postReplies.setStatus(postRepliesRequest.status);
    return postReplies;
  }
}
