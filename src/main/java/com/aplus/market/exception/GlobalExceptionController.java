package com.aplus.market.exception;

import com.aplus.market.exception.code.BusinessExceptionCode;
import com.aplus.common.model.vo.ResultVO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;

/**
 * @Author: ldh
 * @Date: 2018/12/17 10:09
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    /**
     * 处理自定义业务异常底层的微服务业务异常
     */
    @ExceptionHandler(value = { Exception.class })
    public ResultVO loginException(Throwable ex) {
        log.error("Exception————>{}",  ex.getMessage());
        return ResultVO.build("0", ex.getMessage());
    }

    /**
     * 处理自定义参数异常
     */
    @ExceptionHandler(value = { BindException.class, MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class,
            UnrecognizedPropertyException.class, InvalidFormatException.class, HttpMessageNotReadableException.class })
    public ResultVO handleBindException(Exception ex) {
        log.error("参数Exception————>{}", ex);
        String message = "输入的参数错误";
        return ResultVO.build(BusinessExceptionCode.PARAM_INVALID.getCode(), message);
    }

    /**
     * 处理普通异常
     */
    @ExceptionHandler(value = { Throwable.class })
    public ResultVO handleCommonException(Throwable ex) {
        log.error(ex.getMessage(), ex);
        if (ex instanceof NumberFormatException) {
            return ResultVO.build(
                    BusinessExceptionCode.PARAM_INVALID.getCode(),
                    BusinessExceptionCode.PARAM_INVALID.getMessage());
        }
        return ResultVO.build(BusinessExceptionCode.SYSTEM_ERROR.getCode(), "未知异常");
    }
}
