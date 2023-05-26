package com.suhuamo.web.common.enums;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/03/16
 * 请求的各种状态码
 */
public enum CodeEnum {
    SUCCESS(200, "成功"),
    NO_CONTENT(204,"没有内容"),
    UNAUTHORIZED_NONE(401, "未授权"),
    UNAUTHORIZED_EXPIRE(402, "授权值已经过期，请附带有效期内的token"),
    UNAUTHORIZED_ERROR(403, "授权值错误，请附带正确的token"),
    TIMED_OUT(408, "请求超时"),
    PARAM_ERROR(500, "参数错误"),

    SERVER_ERROR(999,"抱歉，系统繁忙，请稍后重试！");
    /**
     *  状态码
     */
    private Integer code;
    /**
     *  状态码的描述
     */
    private String desc;


    CodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
