package com.aplus.market.service;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

/**
 * jwt
 * @Author: ldh
 * @Date: 2019/1/5 15:52
 */
public interface JwtService {
    /**
     * 生成秘钥
     * @return
     */
    SecretKey generalKey();

    /**
     * 生成jwt
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    String createJWT(String id, String issuer, String subject, long ttlMillis) throws Exception;

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    Claims parseJWT(String jwt);
}
