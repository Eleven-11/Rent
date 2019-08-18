package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxUserController
 * @Description 微信用户相关操作
 * @Author Lingling00
 * @DATE 7/29/2019 15:06
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxUser")
public class WxUserController {
    @Autowired
    private WxUserService wxUserService;

    /**
     * @description 获取微信用户信息
     * @param request 需要查看的系统用户唯一id-userId,用户本人的id --myUserId
     * @return* 用户id -- wxUserId,
     *         微信用户openID -- openId,
     *         微信用户unionId -- unionId,
     *         用户头像 -- wxAvatar,
     *         用户昵称 -- wxNickname,
     *         性别 -- wxGender,
     *         城市 -- wxCity,
     *         省份 -- wxProvince,
     *         国家 -- wxCountry,
     *         粉丝数量 -- fans,
     *         关注者数量 -- followers,
     *         是否关注该用户 -- isFollow,
     *         用户简介 -- summary，
     *         用户发布总条数 -- releaseNum
     **/
    @GetMapping("/getWxUserInfo")
    public JSONObject getWxUserInfo(HttpServletRequest request) {
        JSONObject jsonObject = CommonUtil.request2Json(request);
        System.out.println(wxUserService.getWxUserInfo(jsonObject));
        return wxUserService.getWxUserInfo(CommonUtil.request2Json(request));
    }
    /**
     * @description 修改用户简介
     * @param request 用户id -- userId，用户简介 -- summary
     * @return summary
     **/
    @RequiresPermissions("wx:user:update")
    @PostMapping("/updateWxUserSummary")
    public JSONObject updateWxUserSummary(HttpServletRequest request) {
        return wxUserService.updateWxUserSummary(CommonUtil.request2Json(request));
    }


}
