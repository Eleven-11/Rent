package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserInformationService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: chens
 * @description: 微信用户消息控制层
 * @date: 2019-07-31 11:30
 */
@Controller
@RequestMapping("/wxInfor")
public class WxUserInformationController {

    @Autowired
    private WxUserInformationService wxUserInformationService;

    @PostMapping("/news")
    public JSONObject getNewInformation(HttpServletRequest request) {
        try {
            return wxUserInformationService.getNewInfomation(CommonUtil.request2Json(request));
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }
}
