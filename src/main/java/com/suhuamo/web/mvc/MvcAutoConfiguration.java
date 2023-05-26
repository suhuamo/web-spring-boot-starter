package com.suhuamo.web.mvc;

import com.suhuamo.web.jwt.JwtInterceptor;
import com.suhuamo.web.jwt.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/05/26
 */
@Configuration
@ConditionalOnProperty(prefix = "suhuamo.web", name = {"enable", "mvc.enable"}, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(MvcProperties.class)
@Import(JwtInterceptor.class)
public class MvcAutoConfiguration {

    private MvcProperties mvcProperties;
    private JwtInterceptor jwtInterceptor;

    public MvcAutoConfiguration(MvcProperties mvcProperties, JwtInterceptor jwtInterceptor) {
        this.mvcProperties = mvcProperties;
        this.jwtInterceptor = jwtInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public MyWebMvcConfigurer myWebMvcConfigurer() {
        return new MyWebMvcConfigurer(jwtInterceptor, mvcProperties);
    }
}
