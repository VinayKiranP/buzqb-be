package com.buz.buzqb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.buz.buzqb.entity.Business;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class BusinessServiceTest {

  @Autowired
  private BusinessService businessService;

  @Test
  void getAllBusiness() {
    List<Business> businessList = businessService.getAllBusiness();
    assertEquals(businessList.isEmpty(),false);
  }

  @Test
  void getBusinessById() {
  }

  @Test
  void saveBusiness() {
  }

  @Test
  void updateBusiness() {
  }

  @Test
  void deleteBusiness() {
  }
}