package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wxUser")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    /**
     *功能描述:获取用户列表
     *@param jsonObject(包括用户昵称nickname、最近活跃时间段dateRange,参数都为空时则查询全部用户列表)
     *@return 用户列表(包括用户昵称nickname、用户性别gender、地区region、注册时间createTime、最近活跃时间updateTime)
     */
    @GetMapping("/getWxUserlist")
    public JSONObject getWxUserList(HttpServletRequest request) {
        return wxUserService.getWxUserList(CommonUtil.request2Json(request));
    }
    /**
     *@description:用户无微信授权登录时，以游客身份登录，后台生成游客信息
     *@param request(无，调用本接口自动生成游客信息)
     *@return java.lang.String（生成的游客标识符（userId））
     */
    @PostMapping("/tpyLogin")
    public String insertVisitor(HttpServletRequest request) {
        return wxUserService.insertVisitor(CommonUtil.request2Json(request));
    }

}
