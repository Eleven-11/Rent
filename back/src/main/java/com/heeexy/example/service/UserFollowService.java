package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 用户关注/粉丝业务逻辑
 * @date: 2019-07-23 17:37
 */
public interface UserFollowService {

    /**
     * 获取用户关注列表
     * @param jsonObject
     * @return
     */
    JSONObject getUserFollowList(JSONObject jsonObject);

    /**
     * 获取用户粉丝列表
     * @param jsonObject
     * @return
     */
    JSONObject getUserFansList(JSONObject jsonObject);

    /**
     * 更新用户关注信息（首次关注、取关、取关后重新关注）
     * @param jsonObject
     * @return
     */
    JSONObject updateUserFollow(JSONObject jsonObject);
}
