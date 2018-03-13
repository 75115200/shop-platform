package com.shop.shopping.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.shop.base.item.entity.Item;
import com.shop.common.base.BaseResult;
import com.shop.common.util.JsonUtil;
import com.shop.file.model.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.shop.common.base.BaseResult.success;

/**
 * 用户相关操作
 * Created by ZZS on 2017/12/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("#{config.key}")
    private String error;

    @RequestMapping("/exception")
    public void exception() {
        System.out.println("test begin...");
        throw new RuntimeException("test1");
    }

    @ExceptionHandler(RuntimeException.class)
    public void handler1(Exception e) {
        e.printStackTrace();
        System.out.println("test1 ...");
    }

}
