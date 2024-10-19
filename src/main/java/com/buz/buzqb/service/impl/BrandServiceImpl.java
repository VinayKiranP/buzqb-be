package com.buz.buzqb.service.impl;

import com.buz.buzqb.dto.BrandRequest;
import com.buz.buzqb.entity.Brand;
import com.buz.buzqb.repository.BrandRepo;
import com.buz.buzqb.service.BrandService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  private final BrandRepo brandRepo;

  @Autowired
  public BrandServiceImpl(BrandRepo brandRepo) {
    this.brandRepo = brandRepo;
  }

  @Override
  public List<Brand> getAllBrand() {
    return brandRepo.findAll();
  }

  @Override
  public Optional<Brand> getBrandById(Integer id) {
    return brandRepo.findById(id);
  }

  @Override
  public Brand saveBrand(BrandRequest brandRequest) {
    Brand brand = brandRequest.requestToBrand(brandRequest);
    return brandRepo.save(brand);
  }

  @Override
  public Brand updateBrand(Brand brand) {
    return brandRepo.save(brand);
  }
}
