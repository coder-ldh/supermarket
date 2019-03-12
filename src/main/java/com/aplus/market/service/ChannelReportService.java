package com.aplus.market.service;

import com.aplus.market.model.vo.ChannelReportVO;

import java.util.List;

/**
 * @Author: ldh
 * @Date: 2019/1/10 16:29
 */
public interface ChannelReportService {

    List<ChannelReportVO> selectList(Long channelId,Integer type, String startTime, String endTime);
}
