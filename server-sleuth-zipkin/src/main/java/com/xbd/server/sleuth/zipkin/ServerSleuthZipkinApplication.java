package com.xbd.server.sleuth.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ServerSleuthZipkinApplication {

    //http://127.0.0.1:9411/zipkin/
    public static void main(String[] args) {
        SpringApplication.run(ServerSleuthZipkinApplication.class, args);
    }

}

