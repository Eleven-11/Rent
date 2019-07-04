package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface PostTypeService {
    /**
     *@description:获取帖子类型列表
     */
    JSONObject getPostTypeList(JSONObject jsonObject);
    /**
     *@description:根据id对图标的名称和图标进行修改
     */
    JSONObject updatePostTypeById(JSONObject jsonObject);

    /**
     *@description:根据id逻辑删除图标信息，即将该条图标信息的删除状态打开
     */
    JSONObject deletePostTypeById(JSONObject jsonObject);

    /**
     *@description:添加帖子类型信息
     */
    JSONObject insertPostType(JSONObject jsonObject);


}
