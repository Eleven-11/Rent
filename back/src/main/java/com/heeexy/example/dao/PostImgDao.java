package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PostImgDao {
    /**
     * @description 获取帖子图片列表
     **/
    List<JSONObject> getPostImgList(JSONObject jsonObject);
}
