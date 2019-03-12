package com.aplus.market.controller;

import com.aplus.market.cache.ThreadCacheMgr;
import com.aplus.market.exception.BusinessException;
import com.aplus.market.exception.code.BusinessExceptionCode;
import com.aplus.market.model.po.UserPO;
import com.aplus.market.model.vo.ResultVO;
import com.aplus.market.model.vo.UserVO;
import com.aplus.market.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author: ldh
 * @Date: 2018/12/18 15:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/list")
    public ResultVO login(UserPO userPO){
        if (userPO == null){
            throw new BusinessException(BusinessExceptionCode.PARAMETER_IS_WRONG);
        }
        String reportDate = userPO.getReportDate();
        if (StringUtils.isEmpty(reportDate)){
            throw new BusinessException(BusinessExceptionCode.PARAMETER_IS_WRONG);
        }
        Long adminId = ThreadCacheMgr.getAdminId();
        Integer pageNum = userPO.getPageNum();
        Integer pageSize = userPO.getPageSize();
        if (pageNum == null || pageSize == null){
            pageNum = 1;
            pageSize = 10;
        }
        String dateFormat;
        if (reportDate.length() == 4){
            dateFormat = "%Y";
        }else if (reportDate.length() == 7){
            dateFormat = "%Y-%m";
        }else {
            dateFormat = "%Y-%m-%d";
        }
//        PageHelper.startPage(pageNum, pageSize);
//        List<UserVO> channelReportVOS = userService.selectListByChannelIdAndReportDate(channelId, reportDate,dateFormat);
//        PageInfo<UserVO> pageInfo = new PageInfo<>(channelReportVOS);
//        PageResultVO pageResult = new PageResultVO(pageNum, pageSize,
//                (int) pageInfo.getTotal(), pageInfo.getList());
//        return ResultVO.success(pageResult);
        return null;
    }
}
