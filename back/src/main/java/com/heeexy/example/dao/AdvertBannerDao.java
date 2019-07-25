package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface AdvertBannerDao {
    /**
     * @description 获取广告栏图片
     * @param 
     * @return 
     **/
    List<JSONObject> getAdvImg(JSONObject jsonObject);
    /**
     * @description 新增广告栏图片信息
     * @param
     * @return 
     **/
    Integer insertAdvImg(JSONObject jsonObject);
    /**
     * @description 逻辑删除广告栏图片（后台管理）
     * @param
     * @return
     **/
    void updateDelAdvImg(JSONObject jsonObject);
    /**
     * @description 更新广告栏图片信息
     * @param
     * @return
     **/
    void updateAdvImg(JSONObject jsonObject);

}
