package com.aplus.market.mapper;

import com.aplus.market.model.vo.ChannelReportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ldh
 * @Date: 2019/1/10 16:18
 */
@Mapper
public interface ChannelReportMapper {

    List<ChannelReportVO> selectList(@Param("channelId") Long channelId,@Param("type") Integer type, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
