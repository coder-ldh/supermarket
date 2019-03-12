package com.aplus.market.aspect;

import com.aplus.market.annotation.Permission;
import com.aplus.market.cache.ThreadCacheMgr;
import com.aplus.market.exception.BusinessException;
import com.aplus.market.exception.code.BusinessExceptionCode;
import com.aplus.market.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 登录验证 AOP
 * @Author: ldh
 * @Date: 2018/11/28 13:24
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {

    @Resource
    AuthService authService;

    @Pointcut("@annotation(com.aplus.market.annotation.Permission)")
    public void adminLoginVerify() {
    }

    /**
     * 拦截所有后台请求过来的接口进行鉴权
     * @param joinPoint
     */
    @Before("adminLoginVerify()")
    public void doAdminAuthVerify(JoinPoint joinPoint) {
        /*判断是否进行权限验证*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        /*从切面中获取当前方法*/
        Method method = signature.getMethod();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new BusinessException(BusinessExceptionCode.NOT_NETWORK);
        }
        Permission action = method.getAnnotation(Permission.class);
        Long channelId = ThreadCacheMgr.getChannelId();
        /*进行权限验证*/
        authService.authRuleVerify(action.value(), channelId);
    }
}
