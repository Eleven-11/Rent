package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PostBaseDao {
    /**
     * @description 获取帖子列表，包括帖子的基本信息
     * @param [jsonObject] 支持通过关键词等参数查询，包括发帖人--poster，关键字--keyword
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostBaseList(JSONObject jsonObject);

    /**
     * @description 计算获取帖子列表的总条数（用于前端分页）
     **/
    int countPost(JSONObject jsonObject);
}
