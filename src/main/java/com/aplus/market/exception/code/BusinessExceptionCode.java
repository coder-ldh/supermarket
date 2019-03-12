package com.aplus.market.exception.code;

/**
 * 统一异常码code
 * @author ldh
 **/
public enum BusinessExceptionCode {

    PARAMETER_IS_WRONG("PARAMETER_403", "参数有误"),
    NO_TOKEN("400", "token不能为空"),
    TOKEN_ERROR("401", "生成token错误"),

    NOT_NETWORK("402", "系统繁忙，请稍后再试。"),
    NO_PERMISSION("403", "无权操作"),
    SYSTEM_ERROR("500", "服务器异常"),
    PARAM_INVALID("502", "参数不正确"),
    ;

    BusinessExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
