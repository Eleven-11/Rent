package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: mac
 * @description: 微信用户授权业务层
 * @date: 2019-08-18 15:43
 */
public interface WxRealmService {

    String wxLogin(String userId);

    JSONObject getInfo();
}
