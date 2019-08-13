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
     * @description 后台获取：获取广告栏图片
     * @param
     * @return
     **/
    List<JSONObject> getBackAdvImgList(JSONObject jsonObject);

    /**
     * 后台获取数据总条数
     * @param jsonObject
     * @return
     */
    Integer countAdvImg(JSONObject jsonObject);

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

    /**
     * @description 对广告栏图片进行排序
     * @param jsonObject
     * @return void
     **/
    void sortAdvImgList(JSONObject jsonObject);

}
