package com.aplus.market.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: ldh
 * @Date: 2019/1/9 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelPO implements Serializable {

    private Integer status;

    private String loginName;

    private Long partnerId;

    private Integer pageNum;

    private Integer pageSize;
}
