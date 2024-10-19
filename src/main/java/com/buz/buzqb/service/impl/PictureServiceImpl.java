package com.buz.buzqb.service.impl;

import com.buz.buzqb.dto.PictureRequest;
import com.buz.buzqb.entity.Picture;
import com.buz.buzqb.repository.PictureRepo;
import com.buz.buzqb.service.PictureService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepo pictureRepo;

  @Autowired
  public PictureServiceImpl(PictureRepo pictureRepo) {
    this.pictureRepo = pictureRepo;
  }

  @Override
  public List<Picture> getAllPicture() {
    return pictureRepo.findAll();
  }

  @Override
  public Optional<Picture> getPictureById(Integer id) {
    return pictureRepo.findById(id);
  }

  @Override
  public Picture savePicture(PictureRequest pictureRequest) {
    Picture picture = pictureRequest.requestToPicture(pictureRequest);
    return pictureRepo.save(picture);
  }

  @Override
  public Picture updatePicture(Picture picture) {
    return pictureRepo.save(picture);
  }
}
