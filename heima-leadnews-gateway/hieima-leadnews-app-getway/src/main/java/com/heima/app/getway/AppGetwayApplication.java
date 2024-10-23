package com.heima.app.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppGetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGetwayApplication.class, args);
    }
}