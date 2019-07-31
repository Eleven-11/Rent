package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: chens
 * @description: 微信用户消息处理接口
 * @date: 2019-07-30 13:59
 */
public interface WxInformationService {


    /**
     * 获取微信用户未收到的最新消息
     * userId   用户ID
     * flagTime 参考时间
     * @param jsonObject
     * @return
     */
    JSONObject getNewInfomation(JSONObject jsonObject);

    /**
     * 获取系统消息
     * @param jsonObject
     * @return
     */
    JSONObject getSysInformation(JSONObject jsonObject);
}
