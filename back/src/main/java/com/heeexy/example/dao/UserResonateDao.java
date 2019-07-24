package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 用户点赞表数据库方法
 * @date: 2019-07-23 17:56
 */
public interface UserResonateDao {
    /**
     * 获取点赞列表条数用于前端分页
     *
     * @param jsonObject
     * @return
     */
    int countPostList(JSONObject jsonObject);

    /**
     * 获取点赞列表(包括点赞人昵称及其头像)
     *
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostLikeList(JSONObject jsonObject);

    /**
     * 添加点赞信息并返回主键
     *
     * @param jsonObject
     */
    void insertPostLike(JSONObject jsonObject);

    /**
     * 取消点赞
     *
     * @param jsonObject
     * @return
     */
    int updateDelPostLike(JSONObject jsonObject);
    /**
     * @description 获取用户点赞过的帖子id
     *
     * @param jsonObject
     * @return
     **/
    List<JSONObject> getUserLikeList(JSONObject jsonObject);

}
