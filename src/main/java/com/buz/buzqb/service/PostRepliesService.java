package com.buz.buzqb.service;

import com.buz.buzqb.entity.PostReplies;
import java.util.List;
import java.util.Optional;

public interface PostRepliesService {

  List<PostReplies> findAllByPostIdOrderByCreatedByDesc(Long postId);

  Optional<PostReplies> getPostRepliesById(Long id);

  PostReplies savePostReplies(PostReplies postReplies);

  PostReplies updatePostReplies(PostReplies postReplies);
}
