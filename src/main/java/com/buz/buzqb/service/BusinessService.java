package com.buz.buzqb.service;

import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.entity.Business;
import java.util.List;
import java.util.Optional;

public interface BusinessService {

    List<Business> getAllBusiness();

    Optional<Business> getBusinessById(Integer id);

    Business saveBusiness(BusinessRequest businessRequest);

    Business updateBusiness(Business business);

    Business deleteBusiness(Business business);

    Optional<Business> getBusinessByEmail(String email);
}
