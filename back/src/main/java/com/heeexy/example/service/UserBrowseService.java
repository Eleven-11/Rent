package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface UserBrowseService {
    /**
     * @description 获取用户浏览过的帖子id列表
     *
     * @return*/
    JSONObject getUserBrowsePostList(JSONObject jsonObject);
     /**
      * @description 添加用户浏览信息，同时更新对应帖子的浏览量
      * @param
      * @return
      **/
     JSONObject insertUserBrowse(JSONObject jsonObject);

    /**
     * @description 获取浏览过指定帖子的用户列表
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    JSONObject getPostBrowseList(JSONObject jsonObject);
}
