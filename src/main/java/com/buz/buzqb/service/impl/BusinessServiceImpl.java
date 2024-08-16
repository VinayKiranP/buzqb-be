package com.buz.buzqb.service.impl;

import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.repository.BusinessRepo;
import com.buz.buzqb.service.BusinessService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
  @Autowired
  private BusinessRepo businessRepo;

  @Override
  public List<Business> getAllBusiness() {
    return businessRepo.findAll();
  }

  @Override
  public Optional<Business> getBusinessById(Integer id){
    return businessRepo.findById(id);
  }

  @Override
  public Business saveBusiness(BusinessRequest businessRequest){
    Business business = businessRequest.requestToBusiness(businessRequest);
    return businessRepo.save(business);
  }

  @Override
  public Business updateBusiness(Business business){
    return businessRepo.save(business);
  }

  @Override
  public Business deleteBusiness(Business business){
    return businessRepo.save(business);
  }

}
