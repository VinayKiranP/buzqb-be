package com.buz.buzqb.service;

import com.buz.buzqb.dto.BrandRequest;
import com.buz.buzqb.entity.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandService {

  List<Brand> getAllBrand();

  Optional<Brand> getBrandById(Integer id);

  Brand saveBrand(BrandRequest roleRequest);

  Brand updateBrand(Brand brand);
}
