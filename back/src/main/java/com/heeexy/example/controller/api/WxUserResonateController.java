package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserResonateService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WxUserResonateController
 * @Description 小程序：用户点赞相关操作
 * @Author Lingling00
 * @DATE 7/16/2019 11:39
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/wxUserLike")
public class WxUserResonateController {
    @Autowired
    private UserResonateService userResonateService;

    /**
     * @description 添加用户点赞信息
     * @param request 帖子id-postId,系统用户id-userId，插入数据库的数据同时包括点赞时间，在service层生成
     * @return 无返回值
     **/
    @PostMapping("/insertPostLike")
    public JSONObject insertPostLike(HttpServletRequest request) {
        return userResonateService.insertPostLike(CommonUtil.request2Json(request));
    }

    /**
     * @description 用户更改点赞状态（在已点过赞的基础上取消赞或取消后重新点赞）
     * @param request 帖子id-postId,系统用户id-userId
     * @return 无返回值
     **/
    @PostMapping("/updateDelPostLike")
    public JSONObject updateDelPostLike(HttpServletRequest request) {
        return userResonateService.updateDelPostLike(CommonUtil.request2Json(request));
    }
    /**
     * @description 获取帖子点赞列表
     * @param request 条数（可选）-limit，帖子Id-postId
     * @return 帖子点赞列表：点赞人头像likeImg和昵称likeNickname
     **/
    @GetMapping("/getPostLikeList")
    public JSONObject getPostLikeList(HttpServletRequest request){
        return userResonateService.getPostLikeList(CommonUtil.request2Json(request));
    }
    /**
     * @description 获取用户点赞过的帖子列表
     * @param request 系统用户id-userId
     * @return
     **/
    @GetMapping("/getUserLikeList")
    public JSONObject getUserLikePostList(HttpServletRequest request){
        return userResonateService.getUserLikePostList(CommonUtil.request2Json(request));
    }
}
