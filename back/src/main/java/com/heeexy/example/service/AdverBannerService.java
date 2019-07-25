package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface AdverBannerService {
    /**
     * @description 获取广告栏图片
     * @param
     * @return
     **/
    List<JSONObject> getAdvImgList(JSONObject jsonObject);
    /**
     * @description 新增广告栏图片信息
     * @param
     * @return
     **/
    JSONObject insertAdvImg(JSONObject jsonObject);
    /**
     * @description 后台管理：逻辑删除广告栏图片
     * @param
     * @return
     **/
    JSONObject updateDelAdvImg(JSONObject jsonObject);
    /**
     * @description 更新广告栏图片信息
     * @param
     * @return
     **/
    JSONObject updateAdvImg(JSONObject jsonObject);
}
