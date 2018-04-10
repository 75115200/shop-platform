package com.shop.shopping.config;

import com.mongodb.MongoClientOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZZS on 2017/12/14.
 */
@Component
public class Config {
    /**
     * 文件下载路径
     */
    public static final String FILE_URL = "http://localhost:10087/file/download?id=";
    
    /**
     * 文件上传路径
     */
    public static final String FILE_UPLOAD_URL = "http://localhost:10087/file/upload.json";
    
    /**
     * Solr服务器地址
     */
    public static final String SOLR_URL = "http://123.207.92.85:8983/solr/collection1/select?wt=json&indent=true&json.wrf=?";
    
    /**
     * 设置thymeleaf的参数
     * @param viewResolver
     */
    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("FILE_URL", FILE_URL);
            vars.put("FILE_UPLOAD_URL", FILE_UPLOAD_URL);
            vars.put("SOLR_URL", SOLR_URL);

            viewResolver.setStaticVariables(vars);
        }
    }
    
//    @Bean
//    @ConditionalOnMissingBean
//    public OrdersDialect enumDialect() {
//        return new OrdersDialect();
//    }
    
    @Resource
    public void setEnumDialect(SpringTemplateEngine templateEngine){
        templateEngine.addDialect(new OrdersDialect());
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
