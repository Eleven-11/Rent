package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;

@RestController
@RequestMapping("/wxuser")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;
    /**
     * 查询微信用户列表
     */
    @RequiresPermissions("wxuser:list")
    @GetMapping("/list")
    public JSONObject getWxUserList(HttpServletRequest request) {
        return wxUserService.getWxUserList(CommonUtil.request2Json(request));
    }

}
