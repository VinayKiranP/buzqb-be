package com.buz.buzqb.config.auth.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CORSFilter implements Filter {

  //  @Value("${app.allowedOrigin}")
  private String allowedOrigin = "http://localhost:3000/";

  //  @Value("${app.cspUrl:}")
  private String cspUrl = "http://localhost:3000/";

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
    response.setHeader("Access-Control-Allow-Credentials", "false");
    response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,PATCH,DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Authorization,Cache-Control,Content-Type");
    response.setHeader("Server", "coke-studio");
    response.setHeader("Content-Security-Policy",
        "connect-src 'self'; form-action 'self' " + cspUrl
            + "; frame-ancestors 'self'; object-src 'self';");
    response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "origin-when-cross-origin");
    response.setHeader("Cache-Control", "no-store");
    chain.doFilter(req, res);
  }
}
