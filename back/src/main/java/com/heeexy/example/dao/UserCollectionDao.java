package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserCollectionDao {
    /**
     * 根据userId查询用户收藏列表
     */
    List<JSONObject> getUserCollList(JSONObject jsonObject);

    /**
     *对用户收藏的帖子列表进行排序
     */
    int updateUserColl(JSONObject jsonObject);

    /**
     *@description:计算用户收藏的帖子数量
     */
    int countCollection(JSONObject jsonObject);
}
