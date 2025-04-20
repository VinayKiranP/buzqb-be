package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Post;
import com.buz.buzqb.repository.PostRepo;
import com.buz.buzqb.service.PostService;
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
public class PostServiceImpl implements PostService {

  private final PostRepo postRepo;

  @Autowired
  public PostServiceImpl(PostRepo postRepo) {
    this.postRepo = postRepo;
  }

  @Override
  public List<Post> findAllByOrderByCreatedByDesc() {
    return postRepo.findAllByOrderByCreatedByDesc();
  }

  @Override
  @Cacheable(value = "post")
  public Optional<Post> getPostById(Long id) {
    var data = postRepo.findById(id);
    var entity = data.map(post -> Hibernate.unproxy(post, Post.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "post", key = "#post.id")
  public Post savePost(Post post) {
    return postRepo.save(post);
  }

  @Override
  @CacheEvict(value = "post", key = "#post.id")
  public Post updatePost(Post post) {
    return postRepo.save(post);
  }
}
