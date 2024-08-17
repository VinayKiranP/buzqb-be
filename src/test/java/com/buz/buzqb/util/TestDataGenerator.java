package com.buz.buzqb.util;

import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.entity.Business;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataGenerator {
  @Autowired
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public BusinessRequest getBusinessData() {
    BusinessRequest businessRequest = new BusinessRequest();
    businessRequest.setName("testName");
    businessRequest.setEmail("testEmail");
    businessRequest.setUsername("testUsername");
    businessRequest.setStatus("active");

    return businessRequest;
  }

  public Business createBusiness() {
    Business business = new Business();
    business.setName("testName");
    business.setEmail("testEmail");
    business.setUsername("testUsername");
    business.setStatus("active");

    sessionFactory.openSession().persist(business);
    return business;
  }
}
