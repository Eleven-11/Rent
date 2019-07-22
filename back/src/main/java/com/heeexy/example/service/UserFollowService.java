package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface UserFollowService {
    /**
     *@description:获取用户关注列表
     */
    JSONObject getUserFollowList(JSONObject jsonObject);

    /**
     *@description:获取用户粉丝列表
     */
    JSONObject getUserFansList(JSONObject jsonObject);
    /**
     * @description 更新用户关注信息（首次关注、取关、取关后重新关注）
     **/
    JSONObject updateUserFollow(JSONObject jsonObject);
}
