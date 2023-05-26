package com.suhuamo.web.handler;

import com.suhuamo.web.common.enums.CodeEnum;
import com.suhuamo.web.common.exception.CustomException;
import com.suhuamo.web.component.R;
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
    public R error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        // 如果是自定义类型
        if(e.getClass() == CustomException.class) {
            //类型转型
            CustomException ce = (CustomException) e;
            R result = R.error(ce.getCodeEnum().getCode(), ce.getMessage());
            return result;
        // 非自定义类型
        }else{
            R result = R.error(CodeEnum.SERVER_ERROR);
            return result;
        }
    }
}
