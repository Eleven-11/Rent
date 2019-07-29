package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: chens
 * @description: 微信用户举报接口定义
 * @date: 2019-07-29 18:13
 */
public interface WxReportService {

    /**
     * 用户举报
     * @param jsonObject
     * @return
     */
    JSONObject userReport(JSONObject jsonObject);
}
