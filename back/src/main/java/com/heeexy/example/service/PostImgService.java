package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PostImgService {
    /**
     * @description 获取帖子图片列表
     * */
    List<JSONObject> getPostImgList(JSONObject jsonObject);
}
