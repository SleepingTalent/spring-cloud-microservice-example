package com.noveria.musicservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.noveria.musicservice")
@Import({SecurityConfiguration.class, SwaggerConfiguration.class})
public class CloudConfiguration {
}
