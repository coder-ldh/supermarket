package com.aplus.market.service;

import com.aplus.market.model.vo.UserVO;

import java.util.List;

/**
 * @Author: ldh
 * @Date: 2019/1/9 11:11
 */
public interface UserService {


    List<UserVO> selectListByChannelIdAndReportDate(Long channelId, String reportDate,String dateFormat);
}
