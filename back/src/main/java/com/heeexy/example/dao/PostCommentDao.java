package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 评论表数据库方法
 * @date: 2019-07-23 17:51
 */
public interface PostCommentDao {

    /**
     * 获取帖子评论列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostCommentList(JSONObject jsonObject);

    /**
     * 计算评论条数用于分页
     * @param jsonObject
     * @return
     */
    int countComments(JSONObject jsonObject);

    /**
     * 添加评论信息
     * @param jsonObject
     * @return
     */
    int insertComment(JSONObject jsonObject);

    /**
     * 逻辑删除评论
     * @param jsonObject
     */
    void updateDelCommentById(JSONObject jsonObject);

    /**
     * 获取评论状态信息（是否删除）
     * @param jsonObject
     * @return
     */
    int getCommentStatus(JSONObject jsonObject);
    /**
     * @description 获取指定用户评论列表
     * @param jsonObject 用户id-userId
     * @return 
     **/
    List<JSONObject> getUserCommentList(JSONObject jsonObject);
}
