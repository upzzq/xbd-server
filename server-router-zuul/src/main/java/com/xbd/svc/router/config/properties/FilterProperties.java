package com.xbd.svc.router.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = FilterProperties.FILTER_PREFIX)
@Data
public class FilterProperties {

    public static final String FILTER_PREFIX = "xbd.filter";

    /**
     * 登录过滤器白名单(白名单内的不拦截)
     */
    private List<String> allowPaths = new ArrayList<>();

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins = new ArrayList<>();

}
