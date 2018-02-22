package com.noveria;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminDashboard {

	public static void main(String[] args) {
		SpringApplication.run(AdminDashboard.class, args);
	}
}
