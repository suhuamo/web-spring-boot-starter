package com.suhuamo.web.autoconfig;

import com.suhuamo.web.jwt.JwtInterceptor;
import com.suhuamo.web.cors.CrosProperties;
import com.suhuamo.web.cors.CrosWebMvcConfigurer;
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
@EnableConfigurationProperties(CrosProperties.class)
@Import(JwtInterceptor.class)
public class CrosAutoConfiguration {

    private CrosProperties crosProperties;

    public CrosAutoConfiguration(CrosProperties crosProperties) {
        this.crosProperties = crosProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public CrosWebMvcConfigurer myWebMvcConfigurer() {
        return new CrosWebMvcConfigurer(crosProperties);
    }
}
