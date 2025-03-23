package com.buz.buzqb.config;

import com.buz.buzqb.controller.BaseController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig extends BaseController {

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return () -> Optional.ofNullable(
        authenticatedBusiness().getRoleId() < 3 ? authenticatedBusiness().getId()
            : authenticatedBusiness().getBusinessId());// Replace this with the actual logic for fetching logged-in user  }
  }
}