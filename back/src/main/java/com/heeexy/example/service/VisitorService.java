package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface VisitorService {
    /**
     * 小程序：添加游客信息
     * @param jsonObject
     * @return
     */
    JSONObject insertVisitor(JSONObject jsonObject);

}
