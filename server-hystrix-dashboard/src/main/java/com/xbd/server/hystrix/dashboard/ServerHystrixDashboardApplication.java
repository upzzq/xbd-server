package com.xbd.server.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringCloudApplication
@EnableHystrixDashboard
@EnableTurbine
public class ServerHystrixDashboardApplication {

    //http://localhost:9001/hystrix //hystrix监控
    //http://localhost:9001/turbine.stream  turbine监控
    public static void main(String[] args) {
        SpringApplication.run(ServerHystrixDashboardApplication.class, args);
    }

}

