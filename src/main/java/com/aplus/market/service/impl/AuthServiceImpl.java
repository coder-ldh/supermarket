package com.aplus.market.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.aplus.market.constant.RedisConstant;
import com.aplus.market.exception.PermissionAopException;
import com.aplus.market.exception.code.PermissionExceptionCode;
import com.aplus.market.service.AuthService;
import com.aplus.market.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 授权服务实现
 * @Author: ldh
 * @Date: 2018/12/19 9:22
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    RedisService redisService;

    @Override
    public void authRuleVerify(String authRule, Long adminId) {
        Object o = redisService.get(RedisConstant.CHANNEL_PERMISSION_LIST_ID_ + adminId);
        if (o == null){
            throw new PermissionAopException(PermissionExceptionCode.NO_PERMISSION.getCode(),PermissionExceptionCode.NO_PERMISSION.getMessage());
        }
        List<String> permissionList = JSONObject.parseArray(o.toString(), String.class);
        log.info("[权限拦截][用户adminId]——>{}[权限集合permissionList]——>{}",adminId,permissionList.toString());
        if (authRule != null && authRule.length() > 0) {
            for (String e:permissionList) {
                if (!StringUtils.isEmpty(e) && authRule.equals(e)){
                    return;
                }
            }
            /*无权访问*/
            throw new PermissionAopException(PermissionExceptionCode.NO_PERMISSION.getCode(),PermissionExceptionCode.NO_PERMISSION.getMessage());
        }
    }
}
