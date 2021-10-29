package com.securityinnovation.UrlShortner.config;

import com.sun.tools.javac.util.List;
import lombok.var;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
      var cors = new CorsConfiguration();
      cors.setAllowedOrigins(List.of("*"));
      cors.setAllowedMethods(List.of("*"));
      cors.setAllowedHeaders(List.of("*"));
      return cors;
    }).and().csrf().disable()
          .httpBasic();
    }
}