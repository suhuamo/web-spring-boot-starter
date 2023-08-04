package com.suhuamo.web.exception;

import com.suhuamo.web.enums.CodeEnum;
import lombok.Getter;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/16
 * 自定义异常
 */
@Getter
public class CustomException extends RuntimeException  {

    /**
     * 异常code码
     */
    private final CodeEnum codeEnum;
    /**
     * 异常信息
     */
    private final String message;

    public CustomException(CodeEnum codeEnum, String message) {
        this.codeEnum = codeEnum;
        this.message = message;
    }

    public CustomException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
        this.message = codeEnum.getDesc();
    }
}
