package com.shop.shopping.controller;

import com.shop.common.base.BaseResult;
import com.shop.common.base.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.shop.common.base.BaseResult.fail;

@ControllerAdvice
public class AdviceController {
    private Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult handleMethodArgumentNotValidException(Exception e) {
        if (e instanceof BindException) {
            return fail(((BindException) e).getFieldError().getDefaultMessage());
        }
        if (e instanceof BusinessException) {
            return fail(e.getMessage());
        }
        logger.error("服务器发生异常", e);
        return fail(e.getMessage());
    }
}
