package com.suhuamo.web.autoconfig;

import com.suhuamo.web.aop.LogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suhuamo
 * @date 2023-06-10
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 切面的自动配置类
 */
@Configuration
@ConditionalOnProperty(prefix = "suhuamo.web", name = {"enable", "log.enable"}, havingValue = "true", matchIfMissing = true)
public class AopAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
}
