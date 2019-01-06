package com.xbd.svc.actuator.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class ServerActuatorAdminApplication {

    //http://localhost:8000/#/
    public static void main(String[] args) {
        SpringApplication.run(ServerActuatorAdminApplication.class, args);
    }

}

