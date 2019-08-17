package com.heeexy.example.dao;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface NavigationDao {
    /**
     * @description 获取导航栏已置顶帖子的条数
     * @param jsonObject
     * @return int
     **/
    int getNavigationNum(JSONObject jsonObject);

    /**
     * @description 更新板块置顶帖子数量
     * @param
     * @return
     **/
    void updateTopNum(JSONObject jsonObject);

    /**
     * @description 获取置顶模块列表
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    List<JSONObject> getNavigationTitle(JSONObject jsonObject);
}
