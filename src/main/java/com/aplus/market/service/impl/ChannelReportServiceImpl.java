package com.aplus.market.service.impl;

import com.aplus.market.mapper.ChannelReportMapper;
import com.aplus.market.model.vo.ChannelReportVO;
import com.aplus.market.service.ChannelReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ldh
 * @Date: 2019/1/10 16:30
 */
@Service
public class ChannelReportServiceImpl implements ChannelReportService {

    @Resource
    ChannelReportMapper channelReportMapper;

    @Override
    public List<ChannelReportVO> selectList(Long channelId,Integer type, String startTime, String endTime) {
        return channelReportMapper.selectList(channelId,type,startTime,endTime);
    }
}
