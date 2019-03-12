package com.aplus.market.exception;

import com.aplus.market.exception.code.PermissionExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ldh
 * @Date: 2018/12/19 10:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionAopException extends RuntimeException {
    /**
     * 异常编码
     */
    private String code;

    /**
     * 异常内容
     */
    private String message;

    /**
     * 数据
     */
    private Object data;
    private static final long serialVersionUID = 5131156211494399588L;

    public PermissionAopException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionAopException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public PermissionAopException(Throwable cause) {
        super(cause);
    }

    public PermissionAopException(String message) {
        super(message);
    }

    public PermissionAopException(PermissionExceptionCode permissionExceptionCode) {
        this.code = permissionExceptionCode.getCode();
        this.message = permissionExceptionCode.getMessage();
    }
}
