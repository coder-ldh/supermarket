package com.aplus.market.service.impl;

import com.aplus.market.service.RedisService;
import com.aplus.market.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ldh
 * @Date: 2018/12/18 14:49
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    protected RedisUtil redisUtil;

    /**
     * 写入redis缓存（不设置expire存活时间）
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value){
        redisUtil.set(key,value);
        return redisUtil.exists(key);
    }

    /**
     * 写入redis缓存（设置expire存活时间）
     * @param key
     * @param value
     * @param expire
     * @return
     */
    @Override
    public boolean set(final String key, String value, int expire){
        redisUtil.set(key,value,expire);
        return redisUtil.exists(key);
    }


    /**
     * 读取redis缓存
     * @param key
     * @return
     */
    public Object get(final String key){
        return redisUtil.get(key);
    }

    /**
     * 判断redis缓存中是否有对应的key
     * @param key
     * @return
     */
    public boolean exists(final String key){
        return redisUtil.exists(key);
    }

    /**
     * redis根据key删除对应的value
     * @param key
     * @return
     */
    public void remove(final String key){
        redisUtil.del(key);
    }

    /**
     * redis根据keys批量删除对应的value
     * @param keys
     * @return
     */
    public void remove(final String... keys){
        for(String key : keys){
            remove(key);
        }
    }
}
