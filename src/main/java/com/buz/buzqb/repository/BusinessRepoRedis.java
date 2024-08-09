package com.buz.buzqb.repository;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.entity.Business;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessRepoRedis {

  @Autowired
  private RedisTemplate redisTemplate;

  public List<Business> findAll() {
    return redisTemplate.opsForHash().values(Constants.REDIS_HASH_KEY_BUSINESS);
  }

  public Optional<Business> findById(Integer id) {
    return (Optional<Business>) redisTemplate.opsForHash()
        .get(Constants.REDIS_HASH_KEY_BUSINESS, id);
  }

  public Business save(Business business) {
    redisTemplate.opsForHash()
        .put(Constants.REDIS_HASH_KEY_BUSINESS, business.getId(), business);
    return business;
  }

// Delete Method On Redis
//  public Long deleteBusiness(Integer id) {
//    return redisTemplate.opsForHash().delete(Constants.REDIS_HASH_KEY_BUSINESS, id);
//  }
}
