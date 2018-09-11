package com.noveria.musicservice.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().and()
                .authorizeRequests().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources", "/configuration/security", "/swagger-ui.html",
                "/webjars/**", "/swagger-resources/configuration/ui", "/actuator/*").permitAll()
                .anyRequest().authenticated();
    }

}
