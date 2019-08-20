package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserBrowseService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.LocalLock;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxUserBrowseController
 * @Description 用户浏览相关操作
 * @Author Lingling00
 * @DATE 7/25/2019 09:59
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxUserBrowse")
public class WxUserBrowseController {
    @Autowired
    private UserBrowseService userBrowseService;
    /**
     * @description 获取用户浏览过的帖子列表
     * @param request 系统用户id-userId
     * @return
     **/
    @RequiresPermissions("wx:browse:postList")
    @GetMapping("/getUserBrowseList")
    public JSONObject getUserBrowsePostList(HttpServletRequest request){
        return userBrowseService.getUserBrowsePostList(CommonUtil.request2Json(request));
    }

    /**
     * @description 获取浏览过指定帖子的用户列表
     * @param request 帖子id-postId
     * @return
     **/
    @RequiresPermissions("wx:browse:userList")
    @GetMapping("/getPostBrowseList")
    public JSONObject getPostBrowseList(HttpServletRequest request){
        return userBrowseService.getPostBrowseList(CommonUtil.request2Json(request));
    }
    /**
     * @description 添加用户浏览信息，同时更新对应帖子的浏览量
     * @param request 系统用户id - userId，帖子id - postId
     * @return com.alibaba.fastjson.JSONObject
     **/
    @LocalLock(key = "wxUserBrowse:arg[0]")
    @PostMapping("/insertUserBrowse")
    public JSONObject insertUserBrowse(HttpServletRequest request){
        return userBrowseService.insertUserBrowse(CommonUtil.request2Json(request));
    }
}
