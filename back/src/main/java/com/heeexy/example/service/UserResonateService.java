package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 用户点赞业务逻辑
 * @date: 2019-07-23 17:38
 */
public interface UserResonateService {

    /**
     * 获取帖子点赞列表
     * @param jsonObject
     * @return
     */
    JSONObject getPostLikeList(JSONObject jsonObject);

    /**
     * 添加帖子点赞信息
     * @param jsonObject
     * @return
     */
    JSONObject insertPostLike(JSONObject jsonObject);

    /**
     * 取消点赞信息
     * @param jsonObject
     * @return
     */
    JSONObject updateDelPostLike(JSONObject jsonObject);
    /**
     * @description 获取用户点赞过的帖子id列表
     *
     * @return*/
    JSONObject getUserLikePostList(JSONObject jsonObject);
}
