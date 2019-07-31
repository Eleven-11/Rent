package com.heeexy.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author: chens
 * @description: 后台系统消息接口定义
 * @date: 2019-07-31 14:19
 */
public interface BackSysInformationService {

//    JSONObject

    /**
     * 发送系统消息
     * @param jsonObject
     * @return
     */
    JSONObject sendMessage(JSONObject jsonObject) throws IOException;
}
