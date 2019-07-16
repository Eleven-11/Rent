package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface UserResonateService {
    /**
     * @description 获取帖子点赞列表
     **/
    JSONObject getPostLikeList(JSONObject jsonObject);
    /**
     * @description 添加帖子点赞信息
     **/
    JSONObject insertPostLike(JSONObject jsonObject);
    /**
     * @description 取消点赞信息
     **/
    JSONObject updateDelPostLike(JSONObject jsonObject);
}
