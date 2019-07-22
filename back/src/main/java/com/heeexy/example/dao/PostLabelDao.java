package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface PostLabelDao {
    /**
     * @description 获取帖子标签列表
     **/
    List<JSONObject> getPostLabelList(JSONObject jsonObject);
    /**
     *@description:添加帖子标签
     */
    int insertPostLabel(Map<String, Object> map);

    /**
     *@description:更新帖子标签
     */
    void updatePostLabel(JSONObject jsonObject);

    /**
     *@description:删除帖子标签
     */
    void deletePostLabel(JSONObject jsonObject);

    /**
     * @description 帖子标签
     **/
    int countPostLabel(JSONObject jsonObject);
}
