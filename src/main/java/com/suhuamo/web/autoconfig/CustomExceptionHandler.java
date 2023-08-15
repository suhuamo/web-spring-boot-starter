package com.suhuamo.web.autoconfig;

import com.suhuamo.web.common.CodeEnum;
import com.suhuamo.web.common.CustomException;
import com.suhuamo.web.common.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/11
 * 自定义的公共异常处理器
 *      1.声明异常处理器
 *      2.对异常统一处理
 */
@ConditionalOnProperty(prefix = "suhuamo.web", name = {"enable","exception.enable"}, havingValue = "true", matchIfMissing = true)
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        // 如果是自定义类型
        if(e.getClass() == CustomException.class) {
            //类型转型
            CustomException ce = (CustomException) e;
            return ResponseResult.error(ce.getCodeEnum().getCode(), ce.getMessage());
        // 非自定义类型
        }else{
            return ResponseResult.error(CodeEnum.SERVER_ERROR);
        }
    }
}
