package com.aplus.market.controller;

import com.aplus.market.model.po.LoginPO;
import com.aplus.market.model.vo.ResultVO;
import com.aplus.market.service.ChannelService;
import com.aplus.market.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: ldh
 * @Date: 2018/12/18 15:00
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    RedisService redisService;
    @Autowired
    ChannelService channelService;

    @PostMapping(value = "/login")
    public ResultVO login(@RequestBody LoginPO loginPO){
        String name = loginPO.getName();
        String password = loginPO.getPassword();
        String login = channelService.login(name, password);
        return ResultVO.success(login);
    }
}
