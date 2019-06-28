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
    JSONObject updateUserColl(JSONObject jsonObject);
}
