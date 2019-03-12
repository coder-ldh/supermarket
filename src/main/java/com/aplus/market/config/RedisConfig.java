package com.aplus.market.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: ldh
 * @Date: 2018/12/18 11:48
 */
@Configuration
public class RedisConfig{

    @Bean(name = "jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                               @Value("${jedis.pool.host}")String host,
                               @Value("${jedis.pool.port}")int port,
                               @Value("${jedis.pool.timeout}")int timeout,
                               @Value("${jedis.pool.password}")String password,
                               @Value("${jedis.pool.database}")int database) {
        return new JedisPool(config, host, port,timeout,password,database);
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.config.maxTotal:8}")int maxTotal,
                                            @Value("${jedis.pool.config.maxIdle:8}")int maxIdle,
                                            @Value("${jedis.pool.config.maxWaitMillis:-1}")int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;}
}

