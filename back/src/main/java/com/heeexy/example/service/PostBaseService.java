package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface PostBaseService {
    /**
     * @description 获取帖子列表(基本信息)
     **/
    JSONObject getPoseBaseList(JSONObject jsonObject);
    /**
     * @description 更改帖子浏览量、点赞量
     **/
    JSONObject updatePostBase(JSONObject jsonObject);
}
