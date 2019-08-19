package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserFollowService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lingling
 * @description 用户关注列表相关操作
 * @date 2019/6/27 0027
 */
@RestController
@RequestMapping("/wxUserFollow")
public class WxUserFollowController {
    @Autowired
    private UserFollowService userFollowService;

    /**
     *功能描述:根据用户ID查询用户关注列表
     *@param request (用户id--wxUserId)
     *@return 用户关注的所有关注者昵称列表（nickname）
     */
    @GetMapping("/getUserFollowList")
    public JSONObject getUserFollowList(HttpServletRequest request) {
        return userFollowService.getUserFollowList(CommonUtil.request2Json(request));
    }

    /**
     *功能描述:根据用户ID查询用户粉丝列表
     *@param request (用户id--wxUserId)
     *@return 关注指定用户的所有粉丝昵称列表（nickname）
     */
    @GetMapping("/getUserFansList")
    public JSONObject getUserFansList(HttpServletRequest request) {
        return userFollowService.getUserFansList(CommonUtil.request2Json(request));
    }

    /**
     * @description 更新用户关注信息（小程序）
     * @param request 粉丝Id-fanId，关注者Id-followId
     * @return
     **/
    @RequiresPermissions("wx:follow:update")
    @PostMapping("/updateUserFollow")
    public JSONObject insertUserFollow(HttpServletRequest request) {
        return userFollowService.updateUserFollow(CommonUtil.request2Json(request));
    }


}
