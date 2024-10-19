package com.buz.buzqb;

import com.buz.buzqb.common.Constants;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = Constants.SECURITY_SCHEME_NAME, scheme = Constants.SECURITY_SCHEME, type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class BuzqbBeApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuzqbBeApplication.class, args);
  }

}
