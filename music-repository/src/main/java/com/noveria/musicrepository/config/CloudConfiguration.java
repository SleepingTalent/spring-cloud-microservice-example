package com.noveria.musicrepository.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableDiscoveryClient
@ComponentScan("com.noveria.musicrepository")
@Import({SecurityConfiguration.class,DatabaseConfiguration.class, SwaggerConfiguration.class,ZipkinConfiguration.class})
public class CloudConfiguration {
}
