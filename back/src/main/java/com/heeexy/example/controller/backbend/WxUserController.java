package com.heeexy.example.controller.backbend;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Lingling
 * @description: 获取微信用户列表
 * @date: 2019-07-23 17:05
 */
@RestController
@RequestMapping("/wxUser")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    /**
     *@description:获取用户列表
     *@param request (包括用户昵称nickname、最近活跃时间段dateRange,参数都为空时则查询全部用户列表)
     *@return 用户列表(包括用户昵称nickname、用户性别gender、地区region、注册时间createTime、最近活跃时间updateTime)
     */
    @GetMapping("/getWxUserlist")
    public JSONObject getWxUserList(HttpServletRequest request) {
        return wxUserService.getWxUserList(CommonUtil.request2Json(request));
    }



}
