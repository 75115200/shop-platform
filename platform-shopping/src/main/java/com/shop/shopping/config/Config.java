package com.shop.shopping.config;

import com.mongodb.MongoClientOptions;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
            vars.put("FILE_UPLOAD_URL", "http://localhost:10087/file/upload.json");
            vars.put("SOLR_URL", "http://123.207.92.85:8983/solr/collection1/select?wt=json&indent=true&json.wrf=?");

            viewResolver.setStaticVariables(vars);
        }
    }

//    @Bean
//    public SecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//        return securityManager;
//    }
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//        factoryBean.setSecurityManager(securityManager);
//
//        FormAuthenticationFilter manager = new FormAuthenticationFilter();
//        Filter oldFilter = factoryBean.getFilters().putIfAbsent("manager", manager);
//        if (oldFilter != null) {
//            throw new IllegalStateException("存在相同的manager过滤器");
//        }
//
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/public/**", "anon");
//        filterChainDefinitionMap.put("/user/**", "authc");
//        filterChainDefinitionMap.put("/manager/**", "manager");
//
//        factoryBean.setLoginUrl("?login=0");
//        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return factoryBean;
//    }

    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder().socketTimeout(3000).build();
    }

}
