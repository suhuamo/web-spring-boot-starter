package com.suhuamo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author suhuamo
 * @date 2023-05-29
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 */
@MapperScan("org.ycc.demo.mapper")
@ComponentScan("org.ycc.demo")
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}