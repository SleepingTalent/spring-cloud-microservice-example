package com.noveria.musicservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
@Profile("test")
public class ConfigurationForTest {

    @Bean
    public RestTemplate restTemplate() { return new RestTemplate();}
}
