package com.aplus.market.service.impl;

import com.aplus.market.mapper.UserMapper;
import com.aplus.market.model.vo.UserVO;
import com.aplus.market.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ldh
 * @Date: 2019/1/10 16:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<UserVO> selectListByChannelIdAndReportDate(Long channelId, String reportDate ,String dateFormat) {
        return userMapper.selectListByChannelIdAndReportDate(channelId,reportDate,dateFormat);
    }
}
