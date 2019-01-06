package com.xbd.svc.server.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ServerRouterZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRouterZuulApplication.class, args);
    }
}
