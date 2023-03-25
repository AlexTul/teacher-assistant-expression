package com.geeksforless.tuleninov.assistantweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * Main Web class.
 * @version 01
 *
 * @author Oleksandr Tuleninov
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AssistantWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistantWebApplication.class, args);
    }

}
