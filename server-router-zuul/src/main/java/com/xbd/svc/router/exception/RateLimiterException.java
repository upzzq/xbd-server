package com.xbd.svc.router.exception;

import com.xbd.svc.common.exception.BaseServiceException;

public class RateLimiterException extends RuntimeException {

    public RateLimiterException(String message) {
        super(message);
        
    }
}
