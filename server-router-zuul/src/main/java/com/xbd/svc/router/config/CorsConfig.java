package com.xbd.svc.router.config;

import com.xbd.svc.router.config.properties.FilterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


/**
 * zuul 跨域配置
 * 统一跨域处理
 */
@Configuration
public class CorsConfig {

    @Autowired
    private FilterProperties filterProperties;


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 允许跨域的域名, 如果要携带cookie, 不能设置为 *
        config.setAllowedOrigins(Arrays.asList("http://wechat.xbd.com", "http://manage.xbd.com", "http://www.xbd.com"));
        // 是否允许cookie跨域
        config.setAllowCredentials(true);
        // 允许跨域的方法类型 post、get....
        config.addAllowedMethod("*");
        // 是否允许header跨域
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 所有请求都需要拦截
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
