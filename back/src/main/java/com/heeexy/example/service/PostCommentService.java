package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 后台评论管理业务逻辑
 * @date: 2019-07-23 17:07
 */
public interface PostCommentService {

    /**
     * 根据postId获取评论列表
     * @param jsonObject
     * @return
     */
    JSONObject getPostCommentList(JSONObject jsonObject);

    /**
     * 添加评论信息
     * @param jsonObject
     * @return
     */
    int insertComment(JSONObject jsonObject);

    /**
     * 根据评论id逻辑删除评论
     * @param jsonObject
     * @return
     */
    JSONObject updateDelCommentById(JSONObject jsonObject);
    /**
     * @description 获取指定用户评论列表
     * @param
     * @return
     **/
    JSONObject getUserComment(JSONObject jsonObject);
}
