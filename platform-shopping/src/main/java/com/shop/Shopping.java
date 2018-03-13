package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZZS on 2017/12/11.
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan

//@SpringBootApplication

//@Configuration
//@ComponentScan
//@EnableWebMvc

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Shopping {

    public static void main(String[] args) {
        SpringApplication.run(Shopping.class);
    }

}
