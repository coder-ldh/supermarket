package com.aplus.market.controller;

import com.aplus.market.service.ChannelReportService;
import com.aplus.market.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @Author: ldh
 * @Date: 2018/12/18 15:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/channel")
public class ChannelController {

    @Autowired
    ChannelService channelService;
    @Autowired
    ChannelReportService channelReportService;

}
