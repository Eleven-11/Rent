package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserFollowDao {
    /**
     * 根据userId查询用户关注列表
     */
    List<JSONObject> getUserFollowList(JSONObject jsonObject);
    /**
     * 根据userId查询用户粉丝列表
     */
    List<JSONObject> getUserFansList(JSONObject jsonObject);

    /**
     *@description:计算用户关注列表条数
     */
    int countUserFollow(JSONObject jsonObject);

    /**
     *@description:计算用户粉丝列表条数
     */
    int countUserFans(JSONObject jsonObject);
}
