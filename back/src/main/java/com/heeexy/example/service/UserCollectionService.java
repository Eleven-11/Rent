package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 用户收藏业务逻辑
 * @date: 2019-07-23 17:36
 */
public interface UserCollectionService {

    /**
     * 获取用户收藏帖子列表
     * @param jsonObject
     * @return
     */
    JSONObject getUserCollList(JSONObject jsonObject);

    /**
     * 对用户收藏的帖子列表进行排序
     * @param jsonObject
     * @return
     */
    JSONObject sortUserColl(JSONObject jsonObject);

    /**
     * 修改用户收藏信息（新增，取消收藏。重新收藏）
     * @param jsonObject
     * @return
     */
    JSONObject updateUserCollection(JSONObject jsonObject);
}
