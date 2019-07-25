package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 后台帖子管理业务逻辑
 * @date: 2019-07-23 17:07
 */
public interface PostBaseService {

    /**
     * 获取帖子列表(基本信息)
     * @param jsonObject
     * @return
     */
    JSONObject getPostBaseList(JSONObject jsonObject);

    /**
     * 更改帖子浏览量、点赞量、上架、下架
     * @param jsonObject
     * @return
     */
    JSONObject updatePostBase(JSONObject jsonObject);

    /**
     * 发布帖子时插入帖子基本信息
     * @param jsonObject
     * @return
     */
    JSONObject insertPostBase(JSONObject jsonObject, List<JSONObject> postImgList);
}
