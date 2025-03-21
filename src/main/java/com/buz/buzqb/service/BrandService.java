package com.buz.buzqb.service;

import com.buz.buzqb.entity.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandService {

  List<Brand> getAllBrand();

  Optional<Brand> getBrandById(Long id);

  Brand saveBrand(Brand brand);

  Brand updateBrand(Brand brand);
}
