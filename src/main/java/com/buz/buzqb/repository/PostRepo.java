package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByCreatedByDesc();
}
