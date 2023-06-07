package com.suhuamo.web.common;

import com.suhuamo.web.enums.CodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/16 自定义的请求返回类型
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息说明
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 构造函数
     * 
     * @param codeEnum
     * @return null
     */
    private BaseResponse(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
    }

    /**
     * 构造函数
     * 
     * @param codeEnum
     * @param data
     * @return null
     */
    private BaseResponse(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
        this.data = data;
    }

    /**
     * 构造函数
     * 
     * @param code
     * @param message
     * @return null
     */
    private BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     * 
     * @param code
     * @param message
     * @param data
     * @return null
     */
    private BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:"请求成功",data:null}
     * 
     * @param
     * @return BaseResponse
     */
    public static BaseResponse ok() {
        return new BaseResponse(CodeEnum.SUCCESS);
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:"请求成功",data:{data}}
     * 
     * @param data
     * @return BaseResponse
     */
    public static BaseResponse ok(Object data) {
        return new BaseResponse(CodeEnum.SUCCESS, data);
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:{message},data:{data}}
     * 
     * @param message
     * @param data
     * @return BaseResponse
     */
    public static BaseResponse ok(String message, Object data) {
        return new BaseResponse(CodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 返回请求失败的构造函数，默认{code:999,message:抱歉，系统繁忙，请稍后重试！,data:null}
     * 
     * @param
     * @return BaseResponse
     */
    public static BaseResponse error() {
        return new BaseResponse(CodeEnum.SERVER_ERROR);
    }

    /**
     * 返回请求失败的构造函数，默认{code:{codeEnum.code},message:{codeEnum.desc},data:null}
     * 
     * @param codeEnum
     * @return BaseResponse
     */
    public static BaseResponse error(CodeEnum codeEnum) {
        return new BaseResponse(codeEnum);
    }

    /**
     * 返回请求失败的构造函数，默认{code:{code},message:{message},data:null}
     * 
     * @param
     * @return BaseResponse
     */
    public static BaseResponse error(Integer code, String message) {
        return new BaseResponse(code, message);
    }
}
