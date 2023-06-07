package com.suhuamo.web.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.suhuamo.web.jwt.JwtInterceptor;
import com.suhuamo.web.jwt.JwtProperties;
import com.suhuamo.web.jwt.JwtService;
import com.suhuamo.web.jwt.JwtWebMvcConfigurer;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/05/26
 */
@Configuration
@ConditionalOnProperty(prefix = "suhuamo.web", name = {"enable","jwt.enable"}, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

    private JwtProperties jwtProperties;

    public JwtAutoConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtService jwtService() {
        return new JwtService(jwtProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtInterceptor jwtInterceptor(JwtService jwtService) {
        return new JwtInterceptor(jwtProperties, jwtService);
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtWebMvcConfigurer jwtWebMvcConfigurer(JwtInterceptor jwtInterceptor) {
        return new JwtWebMvcConfigurer(jwtInterceptor, jwtProperties);
    }
}
