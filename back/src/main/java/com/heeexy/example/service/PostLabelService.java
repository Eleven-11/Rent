package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface PostLabelService {
    /**
     * @description:获取帖子标签列表
     */
    JSONObject getPostLabelList(JSONObject jsonObject);
    /**
     *@description:添加帖子标签
     */
    JSONObject insertPostLabel(JSONObject jsonObject);

    /**
     *@description:更新帖子标签
     */
    JSONObject updatePostLabel(JSONObject jsonObject);

    /**
     *@description:删除帖子标签
     */
    JSONObject deletePostLabel(JSONObject jsonObject);
}
