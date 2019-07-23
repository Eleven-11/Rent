package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 帖子类型业务逻辑
 * @date: 2019-07-23 17:36
 */
public interface PostTypeService {

    /**
     * 获取帖子类型列表
     * @param jsonObject
     * @return
     */
    JSONObject getPostTypeList(JSONObject jsonObject);

    /**
     * 根据id对图标的名称和图标进行修改
     * @param jsonObject
     * @return
     */
    JSONObject updatePostTypeById(JSONObject jsonObject);

    /**
     * 根据id逻辑删除图标信息，即将该条图标信息的删除状态打开
     * @param jsonObject
     * @return
     */
    JSONObject deletePostTypeById(JSONObject jsonObject);

    /**
     * 添加帖子类型信息
     * @param jsonObject
     * @return
     */
    JSONObject insertPostType(JSONObject jsonObject);


}
