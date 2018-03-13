package com.shop.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ZZS on 2017/12/14.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason = "test2")
public class TestStatusException extends RuntimeException{
    public TestStatusException() {
    }

    public TestStatusException(String message) {
        super(message);
    }

    public TestStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestStatusException(Throwable cause) {
        super(cause);
    }

    public TestStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
