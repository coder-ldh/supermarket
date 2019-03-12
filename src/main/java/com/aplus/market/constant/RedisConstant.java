package com.aplus.market.constant;

/**
 * redis 常量
 * @Author: ldh
 * @Date: 2018/12/19 9:53
 */
public class RedisConstant {
    /**
     * 用户权限集合key
     */
    public static final String CHANNEL_PERMISSION_LIST_ID_ = "CHANNEL_PERMISSION_LIST_ID_";
    /**
     *运营商
     */
    public static final String CHANNEL_ID_TOKEN_ = "CHANNEL_ID_TOKEN_";
    /**
     * token失效时间12小时
     */
    public static final int TOKEN_TTL = 60*60*12;
}
