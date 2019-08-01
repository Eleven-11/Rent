package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 用户限制业务逻辑
 * @date: 2019-07-23 17:38
 */
public interface UserRestrictService {

    /**
     * 对用户设置禁言
     * @param jsonObject
     * @return
     */
    JSONObject insertWxUserRes(JSONObject jsonObject);
    /**
     * @description 手动解除用户禁言结束禁言状态
     * @param
     * @return
     **/
    JSONObject updateDelWxUserRes(JSONObject jsonObject);
}
