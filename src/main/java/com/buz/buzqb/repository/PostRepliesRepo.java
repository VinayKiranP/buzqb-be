package com.buz.buzqb.repository;

import com.buz.buzqb.entity.PostReplies;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepliesRepo extends JpaRepository<PostReplies, Long> {

  List<PostReplies> findAllByPostIdOrderByCreatedByDesc(Long postId);
}
