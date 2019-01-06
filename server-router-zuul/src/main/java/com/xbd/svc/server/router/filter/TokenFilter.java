package com.xbd.svc.server.router.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验请求参数中是否有token，用于校验身份
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 设置过滤器的类型(前置拦截、后置拦截等等...)
     * filterType代表过滤类型
     * PRE: 该类型的filters在Request routing到源web-service之前执行。用来实现Authentication、选择源服务地址等
     * ROUTING：该类型的filters用于把Request routing到源web-service，源web-service是实现业务逻辑的服务。这里使用HttpClient请求web-service。
     * POST：该类型的filters在ROUTING返回Response后执行。用来实现对Response结果进行修改，收集统计数据以及把Response传输会客户端。
     * ERROR：上面三个过程中任何一个出现错误都交由ERROR类型的filters进行处理。
     * 主要关注 pre、post和error。分别代表前置过滤，后置过滤和异常过滤。
     * 如果你的filter是pre的，像上一篇那种，就是指请求先进入pre的filter类，你可以进行一些权限认证，日志记录，或者额外给Request增加一些属性供后续的filter使用。pre会优先按照order从小到大执行，然后再去执行请求转发到业务服务。
     * 再说post，如果type为post，那么就会执行完被路由的业务服务后，再进入post的filter，在post的filter里，一般做一些日志记录，或者额外增加response属性什么的。
     * 最后error，如果在上面的任何一个地方出现了异常，就会进入到type为error的filter中。
     *
     * @return
     */
    @Override
    public String filterType() {
        //FilterConstants常量类中定义了各种filter的行为和顺序等等
        //设置为前置拦截 pre
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 设置拦截器的顺序
     * filterOrder代表过滤器顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        //自定义的拦截器顺序放到 org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter之前(官方推荐的写法)
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * shouldFilter代表这个过滤器是否生效
     * true代表生效，false代表不生效。那么什么情况下使用不生效呢，不生效干嘛还要写这个filter类呢？
     * 其实是有用的，有时我们会动态的决定让不让一个filter生效，譬如我们可能根据Request里是否携带某个参数来判断是否需要生效，或者我们需要从上一个filter里接收某个数据来决定，再或者我们希望能手工控制是否生效（使用如Appolo之类的配置中心，来动态设置该字段）。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Run方法
     * 这个是主要的处理逻辑的地方，我们做权限控制、日志等都是在这里。
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //zuul封装的获取当前请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //从url参数获取token,也可以从cookie, header里获取
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            //返回没有权限
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
