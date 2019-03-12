package com.aplus.market.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ldh
 * @Date: 2019/1/10 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {


    private String reportDate;
    private Long channelId;
    private Integer pageNum;

    private Integer pageSize;
}
