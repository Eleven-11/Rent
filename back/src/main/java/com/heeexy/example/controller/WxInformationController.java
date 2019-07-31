package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxInformationService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: chens
 * @description: 微信用户消息控制层
 * 系统消息 用户消息
 * @date: 2019-07-31 11:30
 */
@RestController
@RequestMapping("/wxInfor")
public class WxInformationController {

    @Autowired
    private WxInformationService wxInformationService;

    /**
     * 获取微信用户未收到的最新消息
     * userId   用户ID
     * flagTime 参考时间
     * @param request
     * @return
     */
    @PostMapping("/news")
    public JSONObject getNewInformation(HttpServletRequest request) {
        try {
            return wxInformationService.getNewInfomation(CommonUtil.request2Json(request));
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/newSys")
    public JSONObject getSysInformation(HttpServletRequest request) {
        try {
            return wxInformationService.getSysInformation(CommonUtil.request2Json(request));
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }
}
