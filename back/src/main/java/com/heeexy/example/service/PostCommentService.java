package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface PostCommentService {

    /**
     * @description 根据postId获取评论列表
     **/
    JSONObject getPostCommentList(JSONObject jsonObject);

    /**
     * @description 添加评论信息
     **/
    int insertComment(JSONObject jsonObject);

    /**
     * @description 逻辑删除评论
     **/
    JSONObject updateDelCommentById(JSONObject jsonObject);
}
