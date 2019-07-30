package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

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
    int insertNavigationTop(JSONObject jsonObject);

    /**
     * @description 查询模块置顶帖子列表
     * @param
     * @return
     **/
    List<JSONObject> getNavigationTopList(JSONObject jsonObject);

    void updateNavigationTop(JSONObject jsonObject);

}
