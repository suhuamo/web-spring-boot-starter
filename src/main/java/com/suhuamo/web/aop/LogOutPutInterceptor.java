package com.suhuamo.web.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

/**
 * @author suhuamo
 * @date 2023-05-29
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 请求响应日志-控制台输出-AOP
 */
@Aspect
@Slf4j
@Component
public class LogOutPutInterceptor {

    /**
     * 执行拦截
     * 同时处理多个类型，execution(* com.suhuamo.web.component.WebController.*(..)) || @annotation(com.suhuamo.web.annotation.LogExecutionTime)
     */
    @SuppressWarnings("all")
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(com.suhuamo.web.annotation.LogExecutionTime)")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // 计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 获取请求路径
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 生成请求唯一 id
        String requestId = UUID.randomUUID().toString();
        // 获取请求参数
        Object[] args = point.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
        // 输出请求日志
        log.info("\nrequest start，id: {}, path: {}, method:{}, ip: {}, params: {}", requestId, request.getRequestURI(), request.getMethod(),
                request.getRemoteAddr(), reqParam); // getRemoteAddr() 无法获取到经过代理后的IP，可以使用 X－FORWARDED－FOR 获取到代理前的IP
        // 执行原方法
        Object result = point.proceed();
        // 输出响应日志
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("\nrequest end, id: {}, cost: {}ms, result: {}", requestId, totalTimeMillis, result);
        return result;
    }
}
