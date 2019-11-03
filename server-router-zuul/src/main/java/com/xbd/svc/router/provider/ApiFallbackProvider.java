package com.xbd.svc.router.provider;

import com.alibaba.fastjson.JSONObject;
import com.xbd.svc.router.enums.ZuulServiceExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class ApiFallbackProvider implements FallbackProvider {

    public ApiFallbackProvider() {
        System.out.println("6666666");
    }

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String router, Throwable cause) {
        if (cause != null && cause.getCause() != null) {
            String reason = cause.getCause().getMessage();
            log.info("router err, router: {}, Excption {}", router, reason);
        }
        return response();
    }

    public ClientHttpResponse response() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {

                ZuulServiceExceptionEnum routerErrorEnum = ZuulServiceExceptionEnum.ROUTER_ERROR;
                JSONObject routerErrorMsg = new JSONObject();
                routerErrorMsg.put("code", routerErrorEnum.getCode());
                routerErrorMsg.put("message", routerErrorEnum.getMessage());
                //返回前端的内容
                return new ByteArrayInputStream(routerErrorMsg.toJSONString().getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }




}
