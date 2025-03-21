package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Brand;
import com.buz.buzqb.repository.BrandRepo;
import com.buz.buzqb.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

  private final BrandRepo brandRepo;

  @Autowired
  public BrandServiceImpl(BrandRepo brandRepo) {
    this.brandRepo = brandRepo;
  }

  @Override
//  @Cacheable("brand")
  public List<Brand> getAllBrand() {
    return brandRepo.findAll();
  }

  @Override
  @Cacheable(value = "brand")
  public Optional<Brand> getBrandById(Long id) {
    var data = brandRepo.findById(id);
    var entity = data.map(brand -> Hibernate.unproxy(brand, Brand.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "brand", key = "#brand.id")
  public Brand saveBrand(Brand brand) {
    return brandRepo.save(brand);
  }

  @Override
  @CacheEvict(value = "brand", key = "#brand.id")
  public Brand updateBrand(Brand brand) {
    return brandRepo.save(brand);
  }
}
