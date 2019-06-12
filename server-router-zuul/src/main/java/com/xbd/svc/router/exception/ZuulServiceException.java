package com.xbd.svc.router.exception;

import com.xbd.svc.common.exception.BaseServiceException;
import com.xbd.svc.router.enums.ZuulServiceExceptionEnum;

public class ZuulServiceException extends BaseServiceException {


    public ZuulServiceException(ZuulServiceExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }
}
