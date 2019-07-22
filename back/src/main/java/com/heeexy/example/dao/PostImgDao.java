package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface PostImgDao {
    /**
     * @description 获取帖子图片列表
     **/
    List<JSONObject> getPostImgList(JSONObject jsonObject);

    /**
     * @description 插入帖子图片列表
     *
     * @param map*/
    JSONObject insertPostImgList(Map<String, Object> map);
}
