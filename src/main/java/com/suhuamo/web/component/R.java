package com.suhuamo.web.component;

import com.suhuamo.web.common.enums.CodeEnum;
import lombok.Data;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/16
 * 自定义的请求返回类型
 */
@Data
public class R<T> {

    /**
     *  状态码
     */
    private Integer code;
    /**
     *  信息说明
     */
    private String message;
    /**
     *  返回数据
     */
    private T data;

    /**
     *  构造函数
     * @param codeEnum
     * @return null
     */
    private R(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
    }

    /**
     *  构造函数
     * @param codeEnum
     * @param data
     * @return null
     */
    private R(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getDesc();
        this.data = data;
    }


    /**
     *  构造函数
     * @param code
     * @param message
     * @return null
     */
    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *  构造函数
     * @param code
     * @param message
     * @param data
     * @return null
     */
    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     *  返回请求成功的构造函数，默认{code:200,message:"请求成功",data:null}
     * @param
     * @return R
     */
    public static R ok() {
        return new R(CodeEnum.SUCCESS);
    }

    /**
     *  返回请求成功的构造函数，默认{code:200,message:"请求成功",data:{data}}
     * @param data
     * @return R
     */
    public static R ok(Object data) {
        return new R(CodeEnum.SUCCESS, data);
    }

    /**
     * 返回请求成功的构造函数，默认{code:200,message:{message},data:{data}}
     * @param message
     * @param data
     * @return R
     */
    public static R ok(String message,Object data) {
        return new R(CodeEnum.SUCCESS.getCode(), message, data);
    }


    /**
     *  返回请求失败的构造函数，默认{code:999,message:抱歉，系统繁忙，请稍后重试！,data:null}
     * @param
     * @return R
     */
    public static R error() {
        return new R(CodeEnum.SERVER_ERROR);
    }

    /**
     *  返回请求失败的构造函数，默认{code:{codeEnum.code},message:{codeEnum.desc},data:null}
     * @param codeEnum
     * @return R
     */
    public static R error(CodeEnum codeEnum) {
        return new R(codeEnum);
    }

    /**
     *  返回请求失败的构造函数，默认{code:{code},message:{message},data:null}
     * @param
     * @return R
     */
    public static R error(Integer code,String message) {
        return new R(code, message);
    }
}
