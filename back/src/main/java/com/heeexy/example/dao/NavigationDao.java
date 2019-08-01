package com.heeexy.example.dao;


import com.alibaba.fastjson.JSONObject;

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
}
