package com.suhuamo.web.autoconfig;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.suhuamo.web.mybatis.MyBatisProperties;
import com.suhuamo.web.mybatis.MyBatisService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/05/26
 */
@Configuration
@ConditionalOnProperty(prefix = "suhuamo.web", name = {"enable", "mybatis.enable"}, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(MyBatisProperties.class)
public class MyBatisAutoConfiguration {

    private MyBatisProperties myBatisProperties;

    public MyBatisAutoConfiguration(MyBatisProperties myBatisProperties) {
        this.myBatisProperties = myBatisProperties;
    }

    /**
     * 新增分页拦截器，并设置数据库类型为mysql
     * 3.4之后的版本写法
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public MyBatisService myBatisPlusService() {
        return new MyBatisService(myBatisProperties);
    }
}
