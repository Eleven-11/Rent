package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PostBaseDao {
    /**
     * @description 获取帖子列表，包括帖子的基本信息
     **/
    List<JSONObject> getPostBaseList(JSONObject jsonObject);

    /**
     * @description 计算获取帖子列表的总条数（用于前端分页）
     **/
    int countPost(JSONObject jsonObject);

    /**
     * @description 更改帖子的点赞数量及浏览量
     **/
    void updatePostBase(JSONObject jsonObject);

    /**
     * @description 发布帖子时插入帖子基本信息
     **/
    String insertPostBase(JSONObject jsonObject);
}
