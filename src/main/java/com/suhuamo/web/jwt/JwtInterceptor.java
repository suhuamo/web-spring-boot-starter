package com.suhuamo.web.jwt;

import com.suhuamo.web.util.ThreadLocalUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.suhuamo.web.common.CodeEnum;
import com.suhuamo.web.common.CustomException;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/07
 * 自定义拦截器
 *      继承HandlerInterceptor
 *
 *      preHandle:进入到控制器方法之前执行的内容
 *          boolean：
 *              true：可以继续执行控制器方法
 *              false：拦截
 *      posthandler：执行控制器方法之后执行的内容
 *      afterCompletion：响应结束之前执行的内容
 *
 * 1.简化获取token数据的代码编写
 *      统一的用户权限校验（是否登录）
 * 2.判断用户是否具有当前访问接口的权限
 */
public class JwtInterceptor implements HandlerInterceptor {

    private JwtProperties jwtProperties;

    private JwtService jwtService;

    public JwtInterceptor(JwtProperties jwtProperties, JwtService jwtService) {
        this.jwtProperties = jwtProperties;
        this.jwtService = jwtService;
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是OPTIONS则默认直接通过，使得OPTIONS的下一个请求能够正常调用--实现跨域的关键
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        // 1.通过request获取请求token信息
        String authorization = request.getHeader(jwtProperties.getHeaderName());
        //判断请求头信息是否为空，或者是否已Bearer开头
        if (StringUtils.hasLength(authorization) && authorization.startsWith(jwtProperties.getHeaderStartWith())) {
            //获取token数据
            String token = authorization.replace(jwtProperties.getHeaderStartWith() + " ", "");
            //解析token获取claims
            Claims claims = jwtService.parseJwt(token);
            if (claims != null) {
                // 判断当前 token 是否过期
                if(jwtService.isExpired(claims)) {
                    throw new CustomException(CodeEnum.UNAUTHORIZED_EXPIRE);
                }
                request.setAttribute(jwtProperties.getClaimsName(), claims);
                return true;
            }
        // 否则抛出未授权信息错误
        } else {
            throw new CustomException(CodeEnum.UNAUTHORIZED_NONE);
        }
        return false;
    }
}
