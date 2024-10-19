package com.buz.buzqb.service;

import com.buz.buzqb.dto.PictureRequest;
import com.buz.buzqb.entity.Picture;
import java.util.List;
import java.util.Optional;

public interface PictureService {

  List<Picture> getAllPicture();

  Optional<Picture> getPictureById(Integer id);

  Picture savePicture(PictureRequest pictureRequest);

  Picture updatePicture(Picture picture);
}
