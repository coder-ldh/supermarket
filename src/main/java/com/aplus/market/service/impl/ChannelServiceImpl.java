package com.aplus.market.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aplus.market.constant.JwtConstant;
import com.aplus.market.constant.RedisConstant;
import com.aplus.market.exception.BusinessException;
import com.aplus.market.exception.LoginException;
import com.aplus.market.exception.code.BusinessExceptionCode;
import com.aplus.market.exception.code.LoginExceptionCode;
import com.aplus.market.mapper.ChannelMapper;
import com.aplus.market.model.Channel;
import com.aplus.market.service.ChannelService;
import com.aplus.market.service.JwtService;
import com.aplus.market.service.RedisService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ldh
 * @Date: 2019/1/9 11:19
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Resource
    JwtService jwtService;
    @Resource
    ChannelMapper channelMapper;
    @Resource
    RedisService redisService;


    @Override
    public String login(String name, String password) {
        /*参数效验*/
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            throw new BusinessException(BusinessExceptionCode.PARAMETER_IS_WRONG);
        }
        Channel channel = new Channel();
        channel.setName(name);
        channel.setStatus(1);
        Channel selectOne = channelMapper.selectOne(channel);
        if (selectOne == null){
            throw new LoginException(LoginExceptionCode.LOGIN_USER_NOT_EXIST);
        }
        String onePassword = selectOne.getPassword();
        String md5Hex = DigestUtils.md5Hex(password);
        if (StringUtils.isNoneEmpty(onePassword) && onePassword.equalsIgnoreCase(md5Hex)){
            /*生成token传给前端*/
            return generateJwt(selectOne);
        }
        throw new LoginException(LoginExceptionCode.Login_PASSWORD_INCORRECT);
    }

    /*生成token并放到redis*/
    public String generateJwt(Channel selectOne) {
        Channel channel = new Channel();
        Long id = selectOne.getId();
        channel.setName(selectOne.getName());
        channel.setPartnerId(selectOne.getPartnerId());
        channel.setId(id);
        String jwt;
        try {
            /*将token存放到redis中*/
            jwt = jwtService.createJWT(id.toString(), "bejing", JSONObject.toJSON(channel).toString(), JwtConstant.JWT_TTL);
            saveToken(id,jwt);
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionCode.TOKEN_ERROR);
        }
        return jwt;
    }

    void saveToken(Long id ,String token){
        redisService.set(RedisConstant.CHANNEL_ID_TOKEN_ +id ,token,RedisConstant.TOKEN_TTL);
    }
}
