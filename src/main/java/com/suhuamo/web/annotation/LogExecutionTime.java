package com.suhuamo.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author suhuamo
 * @date 2023-06-08
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 用来标注该方法需要查询运行时间，并会在日志中输出
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
    String value() default "";
}
