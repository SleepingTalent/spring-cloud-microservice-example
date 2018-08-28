package com.noveria.artistservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.noveria.artistservice.client")
public class FeignConfiguration {
}
