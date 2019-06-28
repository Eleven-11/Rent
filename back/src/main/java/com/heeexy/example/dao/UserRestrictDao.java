package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

public interface UserRestrictDao {
   /**
    *@description:控制用户禁言状态及禁言时间
    *@param jsonObject
    *@return int
    */
    void updateWxUserRes(JSONObject jsonObject);
}
