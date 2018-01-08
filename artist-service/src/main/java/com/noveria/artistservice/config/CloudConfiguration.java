package com.noveria.artistservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableDiscoveryClient
@ComponentScan("com.noveria.artistservice")
@Import({SecurityConfiguration.class, SwaggerConfiguration.class, RibbonConfiguration.class})
public class CloudConfiguration {
}
