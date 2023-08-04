package com.suhuamo.web.autoconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.suhuamo.web.constant.MysqlConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/11
 */
@Slf4j
@ConditionalOnProperty(prefix = "suhuamo.web", name = {"enable","meta-object.enable"}, havingValue = "true", matchIfMissing = true)
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     *  插入时的填充策略
     * @param metaObject
     * @return void
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(MysqlConstant.CREATE_TIME_FILED, LocalDateTime.now(), metaObject);
        this.setFieldValByName(MysqlConstant.UPDATE_TIME_FILED, LocalDateTime.now(), metaObject);
    }

    /**
     *  更新时的时的填充策略
     * @param metaObject
     * @return void
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(MysqlConstant.UPDATE_TIME_FILED, LocalDateTime.now(), metaObject);
    }
}