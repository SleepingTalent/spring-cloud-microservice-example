package com.noveria.musicservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableDiscoveryClient
@ComponentScan("com.noveria.musicservice")
@Import({SecurityConfiguration.class, SwaggerConfiguration.class, RibbonConfiguration.class})
public class CloudConfiguration {
}
