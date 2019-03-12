package com.aplus.market.service;

/**
 * 鉴权
 * @Author: ldh
 * @Date: 2018/12/19 9:22
 */
public interface AuthService {

    /**
     * 鉴权
     * @param authRule 权限标识
     * @param adminId  鉴权对象
     */
    void authRuleVerify(String authRule, Long adminId);
}
