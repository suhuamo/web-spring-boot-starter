package com.suhuamo.web.cors;


import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author suhuamo
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/11
 * 自定义webconfigure类，添加拦截器和配置跨域请求
 */

public class CrosWebMvcConfigurer implements WebMvcConfigurer {

    CrosProperties crosProperties;


    public CrosWebMvcConfigurer(CrosProperties crosProperties) {
        this.crosProperties = crosProperties;
    }

    /**
     * 添加跨域请求映射--全局Cors配置
     *
     * @param registry
     * @return void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //映射服务器中哪些http接口运行跨域访问
                .allowedOrigins(crosProperties.getAllowedOrigins().toArray(new String[0])) // 配置哪些来源有权跨域，可以防止其他人的跨域攻击。如果要修改为 * ，则方法改为 allowedOriginPatterns("*")
                .allowedMethods("*") // 允许跨域的方法，可以单独配置
                .allowedHeaders("*") // 允许跨域的请求头，
                .allowCredentials(true) // 允许请求头中携带参数，如cookie，Authorization等
                .maxAge(3600);
    }
}
