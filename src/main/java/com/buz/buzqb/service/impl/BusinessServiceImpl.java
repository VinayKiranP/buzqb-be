package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Business;
import com.buz.buzqb.repository.BusinessRepo;
import com.buz.buzqb.service.BusinessService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Optional<Business> getBusinessById(Long id) {
    var data = businessRepo.findById(id);
    var entity = data.map(business -> Hibernate.unproxy(business, Business.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  public Business saveBusiness(Business business) {
    return businessRepo.save(business);
  }

  @Override
  public Business deleteBusiness(Business business) {
    return businessRepo.save(business);
  }

  @Override
  public Optional<Business> getBusinessByEmail(String email) {
    return businessRepo.findByEmail(email);
  }
}
