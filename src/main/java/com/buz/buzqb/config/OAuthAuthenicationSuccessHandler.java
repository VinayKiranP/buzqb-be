package com.buz.buzqb.config;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.repository.BusinessRepo;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

  Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);
  private BusinessRepo businessRepo;

  public OAuthAuthenicationSuccessHandler(BusinessRepo businessRepo) {
    this.businessRepo = businessRepo;
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    logger.info("OAuthAuthenticationSuccessHandler");

    // identify the provider

    var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;

    String authorizedClientRegistrationId = oauth2AuthenicationToken.getAuthorizedClientRegistrationId();

    logger.info(authorizedClientRegistrationId);

    var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

    oauthUser.getAttributes().forEach((key, value) ->
      logger.info("oauth user : key:{}, value:{} ", key, value));

    Business business = new Business();
    business.setUsername(UUID.randomUUID().toString());
//    business.setRoleList(List.of(AppConstants.ROLE_USER));
    business.setEmailVerified(true);
    business.setStatus(Constants.MOBILE_VERIFICATION + " " + Constants.PENDING);
    business.setPassword("google");

    if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

      // google attributes
      business.setEmail(oauthUser.getAttribute("email").toString());
      business.setProfilePic(oauthUser.getAttribute("picture").toString());
      business.setName(oauthUser.getAttribute("name").toString());
      business.setProviderUserId(oauthUser.getName());
      business.setProvider(Constants.GOOGLE);
      business.setAbout("This account is created using google.");

    } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

      //gitHub attributes
      String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString()
          : oauthUser.getAttribute("login").toString() + "@gmail.com";
      String picture = oauthUser.getAttribute("avatar_url").toString();
      String name = oauthUser.getAttribute("login").toString();
      String providerUserId = oauthUser.getName();

      business.setEmail(email);
      business.setProfilePic(picture);
      business.setName(name);
      business.setProviderUserId(providerUserId);
      business.setProvider(Constants.GITHUB);
      business.setAbout("This account is created using github");
    } else {
      logger.info("OAuthAuthenticationSuccessHandler: Unknown provider");
    }

    // save the user
    // facebook
    // facebook attributes
    // linkedin

    /*
     *
     *
     *
     * DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
     *
     * logger.info(user.getName());
     *
     * user.getAttributes().forEach((key, value) -> {
     * logger.info("{} => {}", key, value);
     * });
     *
     * logger.info(user.getAuthorities().toString());
     *
     * // data database save:
     *
     * String email = user.getAttribute("email").toString();
     * String name = user.getAttribute("name").toString();
     * String picture = user.getAttribute("picture").toString();
     *
     * // create user and save in database
     *
     * User user1 = new User();
     * user1.setEmail(email);
     * user1.setName(name);
     * user1.setProfilePic(picture);
     * user1.setPassword("password");
     * user1.setUserId(UUID.randomUUID().toString());
     * user1.setProvider(Providers.GOOGLE);
     * user1.setEnabled(true);
     *
     * user1.setEmailVerified(true);
     * user1.setProviderUserId(user.getName());
     * user1.setRoleList(List.of(AppConstants.ROLE_USER));
     * user1.setAbout("This account is created using google..");
     *
     * User user2 = userRepo.findByEmail(email).orElse(null);
     * if (user2 == null) {
     *
     * userRepo.save(user1);
     * logger.info("User saved:" + email);
     * }
     *
     */

    Business user2 = businessRepo.findByEmail(business.getEmail()).orElse(null);
    if (user2 == null) {
      businessRepo.save(business);
      logger.info("user saved: email{}", business.getEmail());
    }

    new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
  }
}