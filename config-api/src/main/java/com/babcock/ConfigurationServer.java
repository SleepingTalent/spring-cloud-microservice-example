package com.babcock;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServer {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServer.class, args);
	}
}
