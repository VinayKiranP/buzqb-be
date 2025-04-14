package com.buz.buzqb.service;

import com.buz.buzqb.entity.Segment;
import java.util.List;
import java.util.Optional;

public interface SegmentService {

  List<Segment> getAllSegment();

  Optional<Segment> getSegmentById(Long id);

  Segment saveSegment(Segment segment);

  Segment updateSegment(Segment segment);
}
