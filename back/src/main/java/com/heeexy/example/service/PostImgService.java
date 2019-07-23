package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 获取帖子图片
 * @date: 2019-07-23 17:34
 */
public interface PostImgService {
    /**
     * 获取帖子图片列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostImgList(JSONObject jsonObject);

    /**
     * 插入帖子图片列表
     * @param jsonObject
     * @return
     */
    JSONObject insertPostImgList(JSONObject jsonObject);
}
