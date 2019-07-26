package com.heeexy.example.controller.backbend;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostCommentService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PostCommentController
 * @Description 帖子评论相关操作
 * @Author Lingling00
 * @DATE 7/16/2019 14:54
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/postComment")
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;

    /**
     * @description 获取帖子评论列表
     * @param request 帖子id-postId
     * @return 帖子评论列表：
     *             评论内容-content,
     *             评论创建时间-createTime,
     *             评论者昵称-startNickname,
     *             评论者性别-startGender,
     *             评论者头像-startImg,
     *             被回复者昵称（可能为null）-receiveNickname,
     *             被回复者头像（可能为null）-receiveImg,
     *             被回复者性别（可能为null）-receiveGender
     **/
    @GetMapping("/getCommentList")
    public JSONObject getCommentList(HttpServletRequest request) {
        System.out.println(CommonUtil.request2Json(request));
        return postCommentService.getPostCommentList(CommonUtil.request2Json(request));
    }

    /**
     * @description 逻辑删除帖子的评论
     * @param request 评论id-commentId
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/updateDelCommentById")
    public JSONObject updateDelCommentById(HttpServletRequest request) {
        return postCommentService.updateDelCommentById(CommonUtil.request2Json(request));
    }
    /**
     * @description 获取指定用户评论列表
     * @param request 系统用户id-userId
     * @return
     **/
    @GetMapping("getUserComment")
    public JSONObject getUserComment(HttpServletRequest request){
        return postCommentService.getUserComment(CommonUtil.request2Json(request));
    }
}
