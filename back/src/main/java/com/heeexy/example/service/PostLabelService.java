package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 帖子标签业务逻辑
 * @date: 2019-07-23 17:35
 */
public interface PostLabelService {

    /**
     * 获取帖子标签列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostLabelList(JSONObject jsonObject);

    /**
     * 添加帖子标签
     * @param jsonObject
     * @return
     */
    JSONObject insertPostLabel(JSONObject jsonObject);

    /**
     * 更新帖子标签
     * @param jsonObject
     * @return
     */
    JSONObject updatePostLabel(JSONObject jsonObject);

    /**
     * 删除帖子标签
     * @param jsonObject
     * @return
     */
    JSONObject updateDelPostLabel(JSONObject jsonObject);
}
