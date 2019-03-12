package com.aplus.market.exception.code;

/**
 * 统一异常码code
 * @author ldh
 **/
public enum LoginExceptionCode {

    LOGIN_USER_NOT_EXIST("LOGIN_400", "用户不存在"),
    Login_PASSWORD_INCORRECT("LOGIN_401", "密码错误"),
    LoginNOT_NETWORK("LOGIN_400", "系统繁忙，请稍后再试。"),
    LoginNO_PERMISSION("LOGIN_400", "无权操作"),
    LoginSYSTEM_ERROR("LOGIN_400", "服务器异常"),
    LoginPARAM_INVALID("LOGIN_400", "参数不正确"),
    ;

    LoginExceptionCode(String code, String message) {
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
