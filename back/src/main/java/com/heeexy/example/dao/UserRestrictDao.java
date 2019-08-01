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

    /**
     * @description 获取用户当前限制状态
     * @param jsonObject
     * @return int
     **/
    int getResStatus(JSONObject jsonObject);

    /**
     * @description 添加用户禁言数据
     * @param
     * @return
     **/
    void insertWxUserRes(JSONObject jsonObject);

    /**
     * @description 手动解除禁言状态
     * @param jsonObject
     * @return void
     **/
    void updateDelWxUserRes(JSONObject jsonObject);
    /**
     * @description 判断用户是否曾被禁言过
     * @param jsonObject
     * @return java.lang.String
     **/
    String getIfRestricted(JSONObject jsonObject);
}
