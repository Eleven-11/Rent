package com.heeexy.example.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 小程序用户表数据库方法
 * @date: 2019-07-23 17:57
 */
public interface WxUserDao {
    
    /**
     * 查询用户数量
     * @param jsonObject
     * @return
     */
    int countWxUser(JSONObject jsonObject);

    /**
     * 查询微信用户列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getWxUserList(JSONObject jsonObject);
    /**
     * @description 新增授权微信用户信息
     * @param jsonObject
     * @return
     **/
    void insertWxUser(JSONObject jsonObject);
    /**
     * @description 获取微信用户信息
     * @param 
     * @return 
     **/
    JSONObject getWxUserInfo(JSONObject jsonObject);
    /**
     * @description 获取用户授权信息
     * @param
     * @return
     **/
    JSONObject getAuthStatus(JSONObject jsonObject);
    /**
     * @description 修改用户信息
     * @param
     * @return
     **/
    void updateWxUserSummary(JSONObject jsonObject);

    /**
     * 校验用户是否存在
     * @param jsonObject
     * @return
     */
    JSONObject queryUser(JSONObject jsonObject);
}
