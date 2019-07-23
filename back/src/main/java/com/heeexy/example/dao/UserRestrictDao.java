package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 小程序限制表数据库操作
 * @date: 2019-07-23 17:56
 */
public interface UserRestrictDao {

   /**
    *@description:控制用户禁言状态及禁言时间
    *@param jsonObject
    *@return int
    */
    void updateWxUserRes(JSONObject jsonObject);
}
