package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 帖子图片表数据库方法
 * @date: 2019-07-23 17:51
 */
public interface PostImgDao {

    /**
     * 获取帖子图片列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostImgList(JSONObject jsonObject);

    /**
     * 插入帖子图片列表
     * @param
     * @return
     */
    JSONObject insertPostImgList(List<JSONObject> postImgList);
}
