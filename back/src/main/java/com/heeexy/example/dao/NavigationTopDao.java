package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

public interface NavigationTopDao {
    /**
     * @description 设置置顶帖子
     **/
    JSONObject insertNavigationTop(JSONObject jsonObject);
}
