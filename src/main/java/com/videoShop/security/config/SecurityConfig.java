package com.videoShop.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration.
 */
@EnableWebSecurity
public class SecurityConfig {

    // Automatically receives AuthenticationEvent messages
    @Bean(name = "loggerListner")
    public LoggerListener loggerListner() {
        return new LoggerListener();
    }

    /**
     * Configure authentication for development and test
     */
    @Order(1)
    @Configuration
    public static class ApiLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/languages", "/api/videos").permitAll()
                    .anyRequest().denyAll();
        }
    }

}