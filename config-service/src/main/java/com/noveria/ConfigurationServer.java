package com.noveria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
//@EnableDiscoveryClient
@EnableEurekaClient
public class ConfigurationServer {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServer.class, args);
	}
}
