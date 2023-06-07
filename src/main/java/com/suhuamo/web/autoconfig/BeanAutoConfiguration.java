package com.suhuamo.web.autoconfig;

import java.net.InetAddress;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author suhuamo
 * @date 2023-06-07
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 做一些初始化的操作
 * 仅用于打印成功输出
 * 配置在 classpath:config/ 目录下的yml才能被其他项目识别到全局配置文件中
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "custom-web", name = "enable", havingValue = "true", matchIfMissing = true)
public class BeanAutoConfiguration implements EnvironmentAware {

    @SneakyThrows
    @Override
    public void setEnvironment(@NotNull Environment environment) {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        log.info("");
        log.info("\n################## 苏花末的自定义web-starter启动成功 ####################"+
                "\n----------------------------------------------------------\n\t" +
                "本次启动的项目的访问地址:\n\t" +
                "本机: \t\t http://localhost:" + port + "/\n\t" +
                "外部访问: \t http://" + ip + ":" + port + "/\n\t" +
                "Swagger文档: \t http://localhost:" + port + "/swagger-ui/index.html\n\t" +
                "Knife4j增强文档: \t http://localhost:" + port + "/doc.html\n\t" +
                "----------------------------------------------------------");
    }
}