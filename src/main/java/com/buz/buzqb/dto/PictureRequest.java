package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Picture;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PictureRequest {

  @NotNull
  @Size(max = 250, message = "name can't exceed 250 characters")
  private String name;
  private String description;
  private String type;
  private int status;

  public Picture requestToPicture(PictureRequest pictureRequest) {
    Picture picture = new Picture();
    picture.setName(pictureRequest.getName());
    picture.setType(pictureRequest.getType());
    picture.setDescription(pictureRequest.getDescription());
    picture.setStatus(pictureRequest.getStatus());
    return picture;
  }
}
