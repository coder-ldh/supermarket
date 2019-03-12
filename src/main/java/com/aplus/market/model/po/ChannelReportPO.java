package com.aplus.market.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ldh
 * @Date: 2019/1/10 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelReportPO {


    private Long channelId;

    private String startTime;

    private String endTime;

    private Integer type;

    private Integer pageNum;

    private Integer pageSize;
}
