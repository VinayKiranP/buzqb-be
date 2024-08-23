package com.buz.buzqb.config;


import com.buz.buzqb.config.auth.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private AuthenticationProvider authenticationProvider;
  private JwtAuthenticationFilter jwtAuthenticationFilter;
  private OAuthAuthenicationSuccessHandler oAuthAuthenicationSuccessHandler;

  public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
      AuthenticationProvider authenticationProvider,
      OAuthAuthenicationSuccessHandler oAuthAuthenicationSuccessHandler) {
    this.authenticationProvider = authenticationProvider;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.oAuthAuthenicationSuccessHandler = oAuthAuthenicationSuccessHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    /*httpSecurity.csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers("/auth/**", "/info", "/swagger-ui/**", "/health", "actuator/**",
            "/swagger-ui.html", "/v3/api-docs/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);*/

    //Above method has been redefined as below due to some of the methods deprecated in spring-security v6+

    //httpSecurity.csrf(csrf -> csrf.disable());

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.authorizeHttpRequests(
        authorize -> authorize.requestMatchers("/auth/**", "/info", "/swagger-ui/**", "/health",
                "actuator/**", "/swagger-ui.html", "/v3/api-docs/**", "/login").permitAll().anyRequest()
            .authenticated());
    httpSecurity.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    httpSecurity.authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    // oauth configurations
    httpSecurity.oauth2Login(oauth -> oauth.successHandler(oAuthAuthenicationSuccessHandler));

//    httpSecurity.oauth2Login(oauth -> {
//      oauth.loginPage("/login");
////      oauth.successHandler(oAuthAuthenicationSuccessHandler);
//    });
//
//    httpSecurity.logout(logoutForm -> {
//      logoutForm.logoutUrl("/do-logout");
//      logoutForm.logoutSuccessUrl("/login?logout=true");
//    });

    return httpSecurity.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(List.of("http://localhost:8005"));
    configuration.setAllowedMethods(List.of("GET", "POST"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}