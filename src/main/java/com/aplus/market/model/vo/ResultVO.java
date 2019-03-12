package com.aplus.market.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * <p>
 *    统一返回包装类
 * </p>
 * @author ldh
 * @since 2018-11-21
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    public static final String SUCCESS_CODE = "200";

    public static final String ERROR_CODE = "0";

    public static final String SUCCESS_DEFAULT_MESSAGE = "操作成功";

    public static final String FAILURE_DEFAULT_MESSAGE = "操作失败";

    private String code;

    private String msg;

    private T data;

    public static ResultVO build(String code, String message) {
        return new ResultVO<>(code, message, null);
    }

    public static ResultVO build(String code, String message, Object data) {
        return new ResultVO<>(code, message, data);
    }

    public static ResultVO success() {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_DEFAULT_MESSAGE, null);
    }

    public static ResultVO success(Object data) {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_DEFAULT_MESSAGE, data);
    }

    public static ResultVO fail() {
        return new ResultVO<>(ERROR_CODE, FAILURE_DEFAULT_MESSAGE, null);
    }

    public static ResultVO fail(String msg) {
        return new ResultVO<>(ERROR_CODE, msg, null);
    }
}
