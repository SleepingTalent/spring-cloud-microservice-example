package com.noveria.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
@ComponentScan("com.noveria")
@Import({SecurityConfiguration.class})
public class CloudConfiguration {
}
