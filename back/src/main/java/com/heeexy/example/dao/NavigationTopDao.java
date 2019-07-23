package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 置顶帖子
 * @date: 2019-07-23 16:55
 */
public interface NavigationTopDao {
    /**
     * 设置置顶帖子
     * @param jsonObject
     * @return
     */
    JSONObject insertNavigationTop(JSONObject jsonObject);
}
