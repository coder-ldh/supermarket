package com.aplus.market.mapper;


import com.aplus.market.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ChannelMapper
 * @Mapper
 * @version : Ver 1.0
 */
@Mapper
public interface UserMapper{

    List<UserVO> selectListByChannelIdAndReportDate(@Param("channelId") Long channelId, @Param("reportDate") String reportDate,@Param("dateFormat") String dateFormat);
}
