package com.aplus.market.exception.code;

/**
 * 统一异常码code
 * @author ldh
 **/
public enum PermissionExceptionCode {

    NO_PERMISSION("PERMISSION_400", "无权操作"),
    TOKEN_EXPIRY_ERROR("PERMISSION_TOKEN_401", "token失效请重新登录"),
    TOKEN_FORMAT_ERROR("PERMISSION_TOKEN_402", "token 格式错误"),
    TOKEN_ERROR("PERMISSION_TOKEN_403", "token 错误"),
    ;

    PermissionExceptionCode(String code, String message) {
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
