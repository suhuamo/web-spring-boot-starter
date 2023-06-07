package com.suhuamo.web.jwt;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author suhuamo
 * @date 2023-06-07
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 */
public class JwtWebMvcConfigurer implements WebMvcConfigurer {
    private JwtInterceptor jwtInterceptor;
    private JwtProperties jwtProperties;

    public JwtWebMvcConfigurer(JwtInterceptor jwtInterceptor, JwtProperties jwtProperties) {
        this.jwtInterceptor = jwtInterceptor;
        this.jwtProperties = jwtProperties;
    }

    /**
     * 添加拦截器
     *
     * @param registry
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor) // 添加自定义拦截器
                .addPathPatterns("/**")// 设置映射规则,即允许哪些接口会被添加到拦截器中
                .excludePathPatterns((jwtProperties.getExcludePathPatterns())); //设置排除的规则，即哪些接口不会被加入到拦截器中
    }
}