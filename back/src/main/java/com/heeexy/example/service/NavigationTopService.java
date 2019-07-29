package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface NavigationTopService {
    /**
     * @description 添加导航栏置顶帖子
     * @param
     * @return
     **/
    JSONObject insertNavigationTop(JSONObject jsonObject);
    /**
     * @description 获取导航栏置顶帖子列表
     * @param
     * @return
     **/
    List<JSONObject> getNavigationTopList(JSONObject jsonObject);
}
