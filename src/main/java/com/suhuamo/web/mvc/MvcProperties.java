package com.suhuamo.web.mvc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/11
 */
@ConfigurationProperties("suhuamo.web.mvc")
@Data
public class MvcProperties {
    /**
     * 设置拦截器排除的规则，默认 /user/login
     */
    private String[] excludePathPatterns = {"/user/login"};
    /**
     * 设置配置哪些来源有权跨域，默认 http://localhost:8080
     */
    private String[] allowedOrigins = {"http://localhost:8080"};

}
