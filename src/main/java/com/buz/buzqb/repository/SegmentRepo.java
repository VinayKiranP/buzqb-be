package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepo extends JpaRepository<Segment, Long> {

}
