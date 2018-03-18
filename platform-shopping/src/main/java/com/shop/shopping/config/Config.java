package com.shop.shopping.config;

import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("FILE_URL", "http://localhost:10087/file/download?id=");
            vars.put("SOLR_URL", "http://123.207.92.85:8983/solr/collection1/select?wt=json&indent=true&json.wrf=?");

            viewResolver.setStaticVariables(vars);
        }
    }
}
