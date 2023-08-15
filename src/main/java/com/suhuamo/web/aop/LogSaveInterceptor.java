package com.suhuamo.web.aop;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author suhuamo
 * @date 2023-08-10
 * @slogan 加油
 * 用户操作日志保存数据库的aop
 */
@Aspect
@Slf4j
@Component
public class LogSaveInterceptor {
    /**
     * 方法对应操作名称
     */
    private final Map<String, String> METHOD_TO_OPERATOR_NAME;

    public LogSaveInterceptor() {
        METHOD_TO_OPERATOR_NAME = new HashMap<>();
        METHOD_TO_OPERATOR_NAME.put("POST", "新增");
        METHOD_TO_OPERATOR_NAME.put("PUT", "修改");
        METHOD_TO_OPERATOR_NAME.put("DELETE", "删除");
    }

    /**
     * 执行拦截
     */
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // 获取路径请求的方式
        String method = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getMethod();
        method = METHOD_TO_OPERATOR_NAME.get(method);
        log.info("数据库保存日志开始记录, {}", method);
        // 执行原方法
        Object result = point.proceed();
        System.out.println("result = " + result);
        return result;
    }
}
