package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Post;
import com.buz.buzqb.entity.PostReplies;
import com.buz.buzqb.repository.PostRepliesRepo;
import com.buz.buzqb.service.PostRepliesService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class PostRepliesServiceImpl implements PostRepliesService {

  private final PostRepliesRepo postRepliesRepo;

  @Autowired
  public PostRepliesServiceImpl(PostRepliesRepo postRepliesRepo) {
    this.postRepliesRepo = postRepliesRepo;
  }

  @Override
  public List<PostReplies> findAllByPostIdOrderByCreatedByDesc(Long postId) {
    return postRepliesRepo.findAllByPostIdOrderByCreatedByDesc(postId);
  }

  @Override
  @Cacheable(value = "postReplies")
  public Optional<PostReplies> getPostRepliesById(Long id) {
    var data = postRepliesRepo.findById(id);
//    var entity = data.isPresent() ? Hibernate.unproxy(data.get(), PostReplies.class) : null;
    var entity = data.map(postReplies -> Hibernate.unproxy(postReplies, PostReplies.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "postReplies", key = "#postReplies.id")
  public PostReplies savePostReplies(PostReplies postReplies) {
    return postRepliesRepo.save(postReplies);
  }

  @Override
  @CacheEvict(value = "postReplies", key = "#postReplies.id")
  public PostReplies updatePostReplies(PostReplies postReplies) {
    return postRepliesRepo.save(postReplies);
  }
}
