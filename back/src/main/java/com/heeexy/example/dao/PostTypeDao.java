package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

public interface PostTypeDao {

    /**
     *@description:添加帖子类型信息
     */
    int insertPostType(JSONObject jsonObject);

    /**
     *@description:根据帖子类型id修改相应信息
     */
    void updatePostTypeById(JSONObject jsonObject);

    /**
     *@description:根据帖子类型id逻辑删除相应信息
     */
    void deletePostTypeById(JSONObject jsonObject);
}
