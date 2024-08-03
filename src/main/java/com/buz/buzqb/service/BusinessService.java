package com.buz.buzqb.service;

import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.model.Business;
import com.buz.buzqb.repository.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    private BusinessRepo businessRepo;

    public List<Business> getAllBusiness() {
        return businessRepo.findAll();
    }

    public Optional<Business> getBusinessById(Integer id){
        return businessRepo.findById(id);
    }

    public Business saveBusiness(BusinessRequest businessRequest){
        Business business = businessRequest.requestToBusiness(businessRequest);
        return businessRepo.save(business);
    }

    public Business updateBusiness(Business business){
        return businessRepo.save(business);
    }

    public Business deleteBusiness(Business business){
        return businessRepo.save(business);
    }
}
