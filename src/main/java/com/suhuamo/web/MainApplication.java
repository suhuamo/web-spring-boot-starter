package com.suhuamo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author suhuamo
 * @date 2023-05-29
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 */
@ComponentScan("com.suhuamo")
@MapperScan("com.suhuamo.example.mapper")
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext application = SpringApplication.run(MainApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        System.out.println("\n----------------------------------------------------------\n\t" +
                "应用程序 系统平台端 正在运行! 访问地址:\n\t" +
                "本机: \t\t http://localhost:" + port + "/\n\t" +
                "外部访问: \t http://" + ip + ":" + port + "/\n\t" +
                "Swagger文档: \t http://localhost:" + port + "/swagger-ui/index.html\n\t" +
                "Knife4j增强文档: \t http://localhost:" + port + "/doc.html\n\t" +
                "----------------------------------------------------------");
    }
}