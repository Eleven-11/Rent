package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PostCommentDao {
    /**
     * @description 获取帖子评论列表
     **/
    List<JSONObject> getCommentList(JSONObject jsonObject);

    /**
     * @description 计算评论条数用于分页
     **/
    int countComments(JSONObject jsonObject);

    /**
     * @description 添加评论信息
     **/
    int insertComment(JSONObject jsonObject);

    /**
     * @description 逻辑删除评论
     **/
    void updateDelCommentById(JSONObject jsonObject);

    /**
     * @description 获取评论状态信息（是否删除）
     **/
    int getCommentStatus(JSONObject jsonObject);
}
