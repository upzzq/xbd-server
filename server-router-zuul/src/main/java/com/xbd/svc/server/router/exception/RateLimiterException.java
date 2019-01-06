package com.xbd.svc.server.router.exception;

public class RateLimiterException extends RuntimeException {

    public RateLimiterException(String message) {
        super(message);
    }
}
