package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserBrowseDao {
    /**
     * @description 用户浏览过的帖子id列表
     * @param
     * @return
     **/
    List<JSONObject> getUserBrowse(JSONObject jsonObject);
    /**
     * @description 添加用户浏览信息
     * @param
     * @return
     **/
    void insertUserBrowse(JSONObject jsonObject);

    /**
     * @description 获取用户浏览状态
     * @param jsonObject
     * @return int
     **/
    JSONObject getBrowseStatus(JSONObject jsonObject);

    /**
     * @description 更新用户浏览时间
     * @param
     * @return
     **/
    void updateUserBrowse(JSONObject jsonObject);
}
