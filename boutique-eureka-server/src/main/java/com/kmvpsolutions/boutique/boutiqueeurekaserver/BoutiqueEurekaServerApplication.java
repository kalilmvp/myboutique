package com.kmvpsolutions.boutique.boutiqueeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BoutiqueEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoutiqueEurekaServerApplication.class, args);
    }

}
