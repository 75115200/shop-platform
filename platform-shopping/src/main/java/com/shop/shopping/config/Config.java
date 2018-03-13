package com.shop.shopping.config;

import org.springframework.stereotype.Component;

/**
 * Created by ZZS on 2017/12/14.
 */
@Component
public class Config {
    private String key = "key";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
