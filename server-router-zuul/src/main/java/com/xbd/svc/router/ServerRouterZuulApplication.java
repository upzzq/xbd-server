package com.xbd.svc.router;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableZuulProxy
@ComponentScan("com.xbd.svc")
public class ServerRouterZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRouterZuulApplication.class, args);
    }
}
