package com.aplus.market.interceptor;

import com.aplus.market.cache.JwtCache;
import com.aplus.market.cache.ThreadCacheMgr;
import com.aplus.market.constant.JwtConstant;
import com.aplus.market.exception.PermissionAopException;
import com.aplus.market.exception.code.PermissionExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ldh
 * @Date: 2019/1/9 10:47
 */
@Component
@Slf4j
@Order(value = -999)
public class BasicInterceptor extends HandlerInterceptorAdapter {

    @Resource
    JwtCache jwtCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
            String token = (request).getHeader(JwtConstant.TOKEN);
        String requestURI = request.getRequestURI();
        log.info("requestURI————>{}",requestURI);
        /*非登录接口*/
        if (!requestURI.contains("/login")){
            if (StringUtils.isNoneEmpty(token)){
                jwtCache.cacheAuth(token);
            }else {
                throw new PermissionAopException(PermissionExceptionCode.TOKEN_ERROR.getCode(),PermissionExceptionCode.TOKEN_ERROR.getMessage());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadCacheMgr.remove();
    }
}
