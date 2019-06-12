package com.xbd.svc.router.config;

import com.google.common.collect.Lists;
import com.xbd.svc.common.properties.SwaggerPrpoerties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@Primary
@Configuration
public class ZuulSwagger2Config implements SwaggerResourcesProvider {

    @Value("${spring.application.name}")
    public String appName;

    @Autowired
    private SwaggerPrpoerties swaggerPrpoerties;

    @Autowired
    RouteLocator routeLocator;

    @Override
    public List<SwaggerResource> get() {
        //利用routeLocator动态引入微服务
        List<SwaggerResource> resources = Lists.newArrayList();
        resources.add(swaggerResource(appName, swaggerPrpoerties.getLocation(), swaggerPrpoerties.getSwaggerVersion()));

        boolean swaggerIsEnable = swaggerPrpoerties.isEnable();
        if (swaggerIsEnable) {
            List<Route> routes = routeLocator.getRoutes();
            routes.forEach(route -> {
                //动态获取
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", swaggerPrpoerties.getLocation()), swaggerPrpoerties.getSwaggerVersion()));
            });
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
