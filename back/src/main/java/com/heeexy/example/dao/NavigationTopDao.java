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
     * @description 后台：查询模块置顶帖子列表
     * @param
     * @return
     **/
    List<JSONObject> getNavigationTopList(JSONObject jsonObject);

    /**
     * @description 小程序：查询模块置顶帖子列表
     * @param
     * @return
     **/
    List<JSONObject> getWxNavigationTopList(JSONObject jsonObject);

    /**
     * @description 更新置顶帖子
     * @param jsonObject
     * @return
     **/
    void updateNavigationTop(JSONObject jsonObject);

    /**
     * @description 对置顶帖子进行排序
     * @param jsonObject
     * @return void
     **/
    void sortNavigationTop(JSONObject jsonObject);



}
