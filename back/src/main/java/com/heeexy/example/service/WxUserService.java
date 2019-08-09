package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 获取用户列表
 * @date: 2019-07-23 17:40
 */
public interface WxUserService {

    /**
     * 后台管理：获取全部用户列表
     * @param jsonObject
     * @return
     */
    JSONObject getWxUserList(JSONObject jsonObject);

    /**
     * 后台管理 更改用户粉丝数量
     * @param jsonObject
     * @return void
     **/
    JSONObject updateWxUserInfo(JSONObject jsonObject);

    /**
     * 后台管理：其他页面使用的查询用户
     * @param jsonObject
     * @return
     */
    JSONObject queryUser(JSONObject jsonObject);

    /**
     * 小程序：添加微信授权用户信息
     * @param jsonObject
     * @return
     **/
    String insertWxUser(JSONObject jsonObject);
    /**
     * 小程序：获取用户信息
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getWxUserInfo(JSONObject jsonObject);
    /**
     * @description 小程序：查看用户是否授权过
     * @param jsonObject openId
     * @return
     **/
    JSONObject getAuthStatus(JSONObject jsonObject);

    /**
     * @description 更新用户简介
     * @param
     * @return
     **/
    JSONObject updateWxUserSummary(JSONObject jsonObject);
}
