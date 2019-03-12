package com.aplus.market.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * @Author: ldh
 * @Date: 2019/1/9 18:01
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                log.debug("get {} = {}", key, value);
            }
        } catch (Exception e) {
            log.error("get {} = {}", new Object[]{key, value, e});
        } finally {
            this.release(jedis);
        }
        return value;
    }

    public String set(String key, String value) {
        return this.set(key, value, 0);
    }

    public String set(String key, String value, int expire) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = this.getResource();
            result = jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
            log.debug("set {} = {}", key, value);
        } catch (Exception e) {
            log.error("set {} = {}", new Object[]{key, value, e});
        } finally {
            this.release(jedis);
        }
        return result;
    }

    public boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error("exists {}", key, e);
        } finally {
            this.release(jedis);
        }
        return result;
    }

    public Long incr(String key) {
        return incrWithExpire(key, 0);
    }

    public Long incrWithExpire(String key, int seconds) {
        Jedis jedis = null;
        Long incr;
        try {
            if (StringUtils.isBlank(key)) {
                incr = null;
                return incr;
            }
            jedis = this.getResource();
            incr = jedis.incr(key);
            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            log.error("incr {} ", key, e);
            return null;
        } finally {
            this.release(jedis);
        }
        return incr;
    }

    public Long incrBy(String key, Long integer) {
        Jedis jedis = null;
        Long incrBy;
        try {
            if (StringUtils.isBlank(key)) {
                incrBy = null;
                return incrBy;
            }
            if (integer == null) {
                integer = 0L;
            }
            jedis = this.getResource();
            incrBy = jedis.incrBy(key, integer);
        } catch (Exception e) {
            log.error("incr {} ", key, e);
            return null;
        } finally {
            this.release(jedis);
        }
        return incrBy;
    }

    public Long incrByWithExpire(String key, Long integer, int seconds) {
        Jedis jedis = null;
        Long incrBy;
        try {
            if (StringUtils.isBlank(key)) {
                incrBy = null;
                return incrBy;
            }
            if (integer == null) {
                integer = 0L;
            }
            jedis = this.getResource();
            incrBy = jedis.incrBy(key, integer);

            if (seconds > 0) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            log.error("incr {} ", key, e);
            return null;
        } finally {
            this.release(jedis);
        }
        return incrBy;
    }

    public Long decrBy(String key, Long integer) {
        Jedis jedis = null;
        Long decrBy;
        try {
            if (StringUtils.isBlank(key)) {
                decrBy = null;
                return decrBy;
            }
            if (integer == null) {
                integer = 0L;
            }
            jedis = this.getResource();
            decrBy = jedis.decrBy(key, integer);
        } catch (Exception e) {
            log.error("incr {} ", key, e);
            return null;
        } finally {
            this.release(jedis);
        }
        return decrBy;
    }

    public Long decr(String key) {
        Jedis jedis = null;
        Long decr;
        try {
            if (StringUtils.isBlank(key)) {
                decr = null;
                return decr;
            }
            jedis = this.getResource();
            decr = jedis.decr(key);
            return decr;
        } catch (Exception e) {
            log.error("decr {} ", key, e);
            return null;
        } finally {
            this.release(jedis);
        }
    }

    /**
     * 删除
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis jedis = null;
        Long decr;
        try {
            if (StringUtils.isBlank(key)) {
                decr = null;
                return decr;
            }
            jedis = this.getResource();
            decr = jedis.del(key);
            return decr;
        } catch (Exception e) {
            log.error("del {} ", key, e);
            return null;
        } finally {
            this.release(jedis);
        }
    }

    /**
     * 获取分布式锁
     *
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public boolean getLock(String lockKey, String requestId, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            String result = jedis.set(lockKey, requestId, "NX", "EX", expireTime);
            if ("OK".equals(result)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("getLock {} ", lockKey, e);
            return false;
        } finally {
            this.release(jedis);
        }
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean unLock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "return redis.call('del', KEYS[1]) else return 0 end";
            jedis = this.getResource();
            Object result = jedis.eval(script, Collections.singletonList(lockKey),
                    Collections.singletonList(requestId));
            if ("OK".equals(result)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("unLock {} ", lockKey, e);
            return false;
        } finally {
            this.release(jedis);
        }
    }

    private Jedis getResource() throws Exception {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            log.error("getResource.", e);
            throw e;
        }
    }

    private void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
