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

    /**
     * @description 用户编辑帖子
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject updatePost(JSONObject jsonObject);

    /**
     * @description 获取要编辑的帖子基本信息及图片
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getUpdatePostInfo(JSONObject jsonObject);
}
