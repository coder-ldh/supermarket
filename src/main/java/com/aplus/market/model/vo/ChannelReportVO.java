package com.aplus.market.model.vo;

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
public class ChannelReportVO {

    private Long id;

    private Long channelId;

    private String channelName;

    private String reportDate;

    private Integer registerNum;

    private Integer authNum;
}
