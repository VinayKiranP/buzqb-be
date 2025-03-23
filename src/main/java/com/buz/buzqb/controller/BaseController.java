package com.buz.buzqb.controller;

import com.buz.buzqb.entity.Business;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

  /**
   * To get the Logged in authenticated business
   * @return Business
   */
  public Business authenticatedBusiness() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (Business) authentication.getPrincipal();
  }

}
