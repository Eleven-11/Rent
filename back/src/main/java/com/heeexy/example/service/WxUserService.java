package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;

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
     * 小程序：添加游客信息
     * @param jsonObject
     * @return
     */
    String insertVisitor(JSONObject jsonObject);
}
