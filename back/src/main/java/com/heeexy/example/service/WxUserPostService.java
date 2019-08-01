package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.WxPageException;

public interface WxUserPostService {

    /**
     * 微信用户的发帖列表
     * 分页加载
     * @param jsonObject
     * @return
     */
    JSONObject getWxUserPostList(JSONObject jsonObject) throws WxPageException;

    /**
     * @description 设置帖子上下架
     * @param jsonObject
     * @return int
     **/
    JSONObject updateOnShelf(JSONObject jsonObject);
}
