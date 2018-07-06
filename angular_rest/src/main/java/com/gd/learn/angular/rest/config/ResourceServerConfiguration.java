package com.gd.learn.angular.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@Profile("auth2")
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println("*******************************");
        http.csrf()
            .disable()
            .anonymous()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS)
            .permitAll()
            .antMatchers("/api/**")
            .authenticated();

    }
}
