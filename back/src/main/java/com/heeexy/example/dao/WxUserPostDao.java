package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface WxUserPostDao {

    /**
     * 获取用户发布的帖子列表
     * 分页加载
     * @param jsonObject
     * @return
     */
    List<JSONObject> getWxUserPostList(JSONObject jsonObject);

    /**
     * 查询帖子是否属于该用户
     * is_del = 0
     * @param jsonObject
     * @return
     */
    JSONObject queryPostByUserIdPostId(JSONObject jsonObject);
    /**
     * @description 设置帖子上下架
     * @param jsonObject
     * @return int
     **/
    int updateOnShelf(JSONObject jsonObject);
}
