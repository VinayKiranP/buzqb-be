package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Brand;
import com.buz.buzqb.entity.Segment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SegmentRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  private String description;
  private int status;
  private String createdBy;

  public Segment requestToSegment(SegmentRequest segmentRequest) {
    Segment segment = new Segment();
    segment.setName(segmentRequest.getName());
    segment.setDescription(segmentRequest.getDescription());
    segment.setStatus(segmentRequest.getStatus());
    segment.setCreatedBy(segmentRequest.getCreatedBy());
    return segment;
  }
}