package com.xbd.svc.router.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * zuul 跨域配置
 * 统一跨域处理也可以在Nginx上解决
 */
//@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        //是否允许cookie跨域
        config.setAllowCredentials(true);
        //允许跨域访问的站点
        config.setAllowedOrigins(Arrays.asList("*"));
        //是否允许header跨域
        config.setAllowedHeaders(Arrays.asList("*"));
        //允许跨域的方法类型 post、get....
        config.setAllowedMethods(Arrays.asList("*"));

        config.setMaxAge(300L);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
