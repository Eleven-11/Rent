package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PostBaseService {
    /**
     * @description 获取帖子列表(基本信息)
     **/
    JSONObject getPoseBaseList(JSONObject jsonObject);
}
