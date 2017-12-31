package com.noveria.musicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
public class MusicServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(MusicServiceApp.class, args);
    }
}
