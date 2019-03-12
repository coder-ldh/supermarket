package com.aplus.market.cache;

import com.alibaba.fastjson.JSONObject;
import com.aplus.market.constant.RedisConstant;
import com.aplus.market.exception.PermissionAopException;
import com.aplus.market.exception.code.PermissionExceptionCode;
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
            Channel channel = JSONObject.parseObject(subject, Channel.class);
            Long partnerId = channel.getPartnerId();
            Long id = channel.getId();
            Object o = redisService.get(RedisConstant.CHANNEL_ID_TOKEN_ + id);
            if (o == null){
                throw new PermissionAopException(PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getCode(),PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getMessage());
            }
            ThreadCacheMgr.pushObject("claims", claims);
            if (partnerId != null){
                ThreadCacheMgr.push("partnerId",partnerId.toString());
            }
            ThreadCacheMgr.push("channelId",id.toString());
        } catch (MalformedJwtException e2){
            throw new PermissionAopException(PermissionExceptionCode.TOKEN_FORMAT_ERROR.getCode(),PermissionExceptionCode.TOKEN_FORMAT_ERROR.getMessage());
        } catch (ExpiredJwtException e3){
            throw new PermissionAopException(PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getCode(),PermissionExceptionCode.TOKEN_EXPIRY_ERROR.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> parseAuth(){
        Jws<Claims> jws = (Jws<Claims>) ThreadCacheMgr.get("claims");
        return (List<String>) jws.getBody().get(String.valueOf(ThreadCacheMgr.getPartnerId()) + "_permission");
    }

    @SuppressWarnings("unchecked")
    public void cacheEntName(){
        Jws<Claims> jws = (Jws<Claims>) ThreadCacheMgr.get("claims");
        ThreadCacheMgr.push("entName", (String) jws.getBody().get(String.valueOf(ThreadCacheMgr.getPartnerId() + "_entName")));
    }

    @SuppressWarnings("unchecked")
    public void cacheUserName(){
        Jws<Claims> jws = (Jws<Claims>) ThreadCacheMgr.get("claims");
        ThreadCacheMgr.push("userName", (String) jws.getBody().get("userName"));
    }

}
