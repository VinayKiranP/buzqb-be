package com.buz.buzqb.config;

import com.buz.buzqb.controller.BaseController;
import com.buz.buzqb.entity.Business;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig extends BaseController {

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return () -> Optional.of(authenticatedBusiness().getId()); // Replace this with the actual logic for fetching logged-in user
  }
}