package com.xbd.svc.server.router.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试后置拦截器,随意在返回结果的头部中增加一个header
 */
@Component
public class AddResponseHeaderFilter extends ZuulFilter {


    @Override
    public String filterType() {
        //后置拦截 post
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        response.setHeader("zzq", "from hk-xbd datas");
        return null;
    }
}
