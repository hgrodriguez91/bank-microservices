package com.devsu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClientMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientMicroserviceApplication.class, args);
    }

}
