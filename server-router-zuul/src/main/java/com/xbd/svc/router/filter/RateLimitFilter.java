package com.xbd.svc.router.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.xbd.svc.router.exception.RateLimiterException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 限流拦截器
 * 使用令牌桶算法
 */

public class RateLimitFilter extends ZuulFilter {

    //使用google guava包中的令牌桶算法,初始化时设置每秒向桶内放入多少个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //限流拦截器需要放在所有pre拦截器的最前面
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()) {
            //当前请求未拿到令牌,抛出异常
            throw new RateLimiterException("当前请求未拿到限流令牌");
        }
        return null;
    }
}
