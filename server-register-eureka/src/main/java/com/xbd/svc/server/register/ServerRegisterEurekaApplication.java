package com.xbd.svc.server.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerRegisterEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRegisterEurekaApplication.class, args);
    }
}
