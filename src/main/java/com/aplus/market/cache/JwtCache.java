package com.aplus.market.cache;

import com.alibaba.fastjson.JSONObject;
import com.aplus.market.constant.RedisConstant;
import com.aplus.market.exception.PermissionAopException;
import com.aplus.market.exception.code.PermissionExceptionCode;
import com.aplus.market.model.Admin;
import com.aplus.market.model.Channel;
import com.aplus.market.service.JwtService;
import com.aplus.market.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ldh
 * @Date: 2019/1/8 17:16
 */
@Component
public class JwtCache {

    @Resource
    JwtService jwtService;
    @Resource
    RedisService redisService;
    /**
     * 将用户信息存到本地线程中
     * @param jwtToken
     */
    public void cacheAuth(String jwtToken){
        try {
            Claims claims = jwtService.parseJWT(jwtToken);
            String subject = claims.getSubject();
            Admin admin = JSONObject.parseObject(subject, Admin.class);
            Long id = admin.getId();
            Object o = redisService.get(RedisConstant.ADMIN_ID_TOKEN_ + id);
            if (o == null){
                throw new PermissionAopException(PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getCode(),PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getMessage());
            }
            ThreadCacheMgr.pushObject("admin",admin);
            ThreadCacheMgr.pushObject("claims", claims);
            ThreadCacheMgr.push("adminId",id.toString());
        } catch (MalformedJwtException e2){
            throw new PermissionAopException(PermissionExceptionCode.TOKEN_FORMAT_ERROR.getCode(),PermissionExceptionCode.TOKEN_FORMAT_ERROR.getMessage());
        } catch (ExpiredJwtException e3){
            throw new PermissionAopException(PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getCode(),PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getMessage());
        }
    }
}
