package com.aplus.market.exception;


import com.aplus.market.exception.code.LoginExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * </p>
 * @author ldh
 * @since 2018-11-21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginException extends BusinessException {
    private static final long serialVersionUID = 3302370961563777293L;

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


    public LoginException(LoginExceptionCode loginExceptionCode){
        this.code = loginExceptionCode.getCode();
        this.message = loginExceptionCode.getMessage();
    }

    public LoginException(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }


}
