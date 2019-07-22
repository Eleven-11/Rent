package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface UserCollectionService {
    /**
     *@description:获取用户收藏帖子列表
     */
    JSONObject getUserCollList(JSONObject jsonObject);

    /**
     *@description:对用户收藏的帖子列表进行排序
     */
    JSONObject sortUserColl(JSONObject jsonObject);

    /**
     * @description 修改用户收藏信息（新增，取消收藏。重新收藏）
     **/
    JSONObject updateUserCollection(JSONObject jsonObject);
}
