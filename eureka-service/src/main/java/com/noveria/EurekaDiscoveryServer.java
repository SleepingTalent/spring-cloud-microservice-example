package com.noveria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServer {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryServer.class, args);
	}
}
