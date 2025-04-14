package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Segment;
import com.buz.buzqb.repository.SegmentRepo;
import com.buz.buzqb.service.SegmentService;
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
public class SegmentServiceImpl implements SegmentService {

  private final SegmentRepo segmentRepo;

  @Autowired
  public SegmentServiceImpl(SegmentRepo segmentRepo) {
    this.segmentRepo = segmentRepo;
  }

  @Override
  public List<Segment> getAllSegment() {
    return segmentRepo.findAll();
  }

  @Override
  @Cacheable(value = "segment")
  public Optional<Segment> getSegmentById(Long id) {
    var data = segmentRepo.findById(id);
    var entity = data.map(segment -> Hibernate.unproxy(segment, Segment.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "segment", key = "#segment.id")
  public Segment saveSegment(Segment segment) {
    return segmentRepo.save(segment);
  }

  @Override
  @CacheEvict(value = "segment", key = "#segment.id")
  public Segment updateSegment(Segment segment) {
    return segmentRepo.save(segment);
  }
}
