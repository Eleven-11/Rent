package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: chens
 * @description: 微信用户消息处理接口
 * @date: 2019-07-30 13:59
 */
public interface WxUserInformationService {


    /**
     * 获取微信用户未收到的最新消息
     * userId   用户ID
     * flagTime 参考时间
     * @param jsonObject
     * @return
     */
    JSONObject getNewInfomation(JSONObject jsonObject);

//    /**
//     * 微信用户发送消息
//     * @param jsonObject
//     * @return
//     */
//    JSONObject sendMessage(JSONObject jsonObject);
}
