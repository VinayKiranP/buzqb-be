package com.buz.buzqb.service;

import com.buz.buzqb.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {

  List<Post> findAllByOrderByCreatedByDesc();

  Optional<Post> getPostById(Long id);

  Post savePost(Post post);

  Post updatePost(Post post);
}
