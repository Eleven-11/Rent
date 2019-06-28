package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface UserRestrictService {
    /**
     *@description:对用户设置禁言
     */
    JSONObject updateWxUserRes(JSONObject jsonObject);
}
