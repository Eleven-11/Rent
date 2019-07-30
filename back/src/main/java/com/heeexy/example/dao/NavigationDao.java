package com.heeexy.example.dao;


import com.alibaba.fastjson.JSONObject;

public interface NavigationDao {
    /**
     * @description 获取导航栏已置顶帖子的条数
     * @param jsonObject
     * @return int
     **/
    int getNavigationNum(JSONObject jsonObject);
}
