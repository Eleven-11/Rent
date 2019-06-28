package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.WxUser;

import java.lang.reflect.Array;
import java.util.List;

public interface WxUserDao {
    /**
     * 查询用户数量
     */
    int countWxUser(JSONObject jsonObject);

    /**
     * 查询微信用户列表
     */
    List<JSONObject> getWxUserList(JSONObject jsonObject);


}
