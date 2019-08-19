package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserFollowService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lingling
 * @description 用户关注列表相关操作
 * @date 2019/6/27 0027
 */
@RestController
@RequestMapping("/userFollow")
public class BackUserFollowController {
    @Autowired
    private UserFollowService userFollowService;

    /**
     *功能描述:根据用户ID查询用户关注列表
     *@param request (用户id--wxUserId)
     *@return 用户关注的所有关注者昵称列表（nickname）
     */
    @RequiresPermissions("wxUser:follow")
    @GetMapping("/getUserFollowList")
    public JSONObject getUserFollowList(HttpServletRequest request) {
        return userFollowService.getUserFollowList(CommonUtil.request2Json(request));
    }

    /**
     *功能描述:根据用户ID查询用户粉丝列表
     *@param request (用户id--wxUserId)
     *@return 关注指定用户的所有粉丝昵称列表（nickname）
     */
    @RequiresPermissions("wxUser:follow")
    @GetMapping("/getUserFansList")
    public JSONObject getUserFansList(HttpServletRequest request) {
        return userFollowService.getUserFansList(CommonUtil.request2Json(request));
    }



}
