package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserCollectionService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lingling
 * @description 用户收藏列表相关操作
 * @date 2019/6/27 0027
 */
@RestController
@RequestMapping("/wxUserCollection")
public class WxUserCollectionController {
    @Autowired
    private UserCollectionService userCollectionService;


    /**
     *功能描述:根据用户ID查询用户收藏的帖子列表
     *@param request (用户id--wxUserId)
     *@return 用户收藏帖子列表（包括发帖人poster、帖子内容content、帖子图片image、收藏时间collTime）
     */
    @GetMapping("/getUserCollList")
    public JSONObject getUserCollList(HttpServletRequest request) {
        return userCollectionService.getUserCollList(CommonUtil.request2Json(request));
    }
    /**
     * @description 更新用户收藏帖子（首次收藏、取消收藏、取消后重新收藏）
     * @param request 用户id-userId，帖子id-postId
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/updateUserCollection")
    public JSONObject updateUserCollection(HttpServletRequest request) {
        return userCollectionService.updateUserCollection(CommonUtil.request2Json(request));
    }
}
