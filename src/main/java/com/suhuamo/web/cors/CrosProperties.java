package com.suhuamo.web.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/11
 */
@ConfigurationProperties("suhuamo.web.cros")
@Data
public class CrosProperties {
    /**
     * 设置跨域是否启动-给出自动补全
     */
    private Boolean enable = true;
    /**
     * 设置配置哪些来源有权跨域，默认 http://localhost:8080
     */
    private List<String> allowedOrigins = Arrays.asList("http://localhost:8080");

}
