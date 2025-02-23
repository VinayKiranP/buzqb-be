package com.buz.buzqb.service.impl;

import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.repository.BusinessRepo;
import com.buz.buzqb.service.BusinessService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

  private final BusinessRepo businessRepo;

  @Autowired
  public BusinessServiceImpl(BusinessRepo businessRepo) {
    this.businessRepo = businessRepo;
  }

  @Override
  public List<Business> getAllBusiness() {
    return businessRepo.findAll();
  }

  @Override
  @Cacheable(value = "business",  key = "{#root.methodName, #id}")
  public Optional<Business> getBusinessById(Long id) {
    var data = businessRepo.findById(id);
    var entity = data.isPresent() ? Hibernate.unproxy(data.get(), Business.class) : null;
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "business", key = "#busines.id")
  public Business saveBusiness(BusinessRequest businessRequest) {
    Business business = businessRequest.requestToBusiness(businessRequest);
    return businessRepo.save(business);
  }

  @Override
  public Business updateBusiness(Business business) {
    return businessRepo.save(business);
  }

  @Override
  @CacheEvict(value = "business", key = "#id")
  public Business deleteBusiness(Business business) {
    return businessRepo.save(business);
  }

  @Override
  public Optional<Business> getBusinessByEmail(String email) {
    return businessRepo.findByEmail(email);
  }
}
