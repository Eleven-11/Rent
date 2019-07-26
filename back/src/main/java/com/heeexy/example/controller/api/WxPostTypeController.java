package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostTypeService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxPostTypeController
 * @Description 小程序帖子类型相关操作
 * @Author Lingling00
 * @DATE 7/26/2019 16:22
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxPostType")
public class WxPostTypeController {
    @Autowired
    private PostTypeService postTypeService;
    /**
     *@description:获取帖子类型列表
     *@param request
     *@return com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/getPostTypelist")
    public JSONObject getPostTypeList(HttpServletRequest request) {
        return postTypeService.getPostTypeList(CommonUtil.request2Json(request));

    }
}
