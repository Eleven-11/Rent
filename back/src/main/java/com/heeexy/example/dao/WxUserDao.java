package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.WxUser;

import java.lang.reflect.Array;
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

}
