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
     * @description 更新帖子点赞信息（首次点赞，取消点赞，取消后重新点赞）
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject updatePostLike(JSONObject jsonObject);
    /**
     * @description 获取用户点赞过的帖子id列表
     *
     * @return*/
    JSONObject getUserLikePostList(JSONObject jsonObject);
}
