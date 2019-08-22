package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.AdvertBannerDao;
import com.heeexy.example.service.AdverBannerService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AdvertBannerServiceImpl
 * @Description 广告栏图片相关操作
 * @Author Lingling00
 * @DATE 7/25/2019 14:20
 * @VERSION 1.0
 **/
@Service
public class AdvertBannerServiceImpl implements AdverBannerService {
    @Autowired
    private AdvertBannerDao advertBannerDao;

    /**
     * @description 获取广告栏图片列表
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    @Override
    public JSONObject getAdvImgList(JSONObject jsonObject) {
        return CommonUtil.successPage(advertBannerDao.getAdvImg(jsonObject));
    }

    /**
     * 后台获取广告列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getBackAdvImgList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = advertBannerDao.countAdvImg(jsonObject);
        List<JSONObject> list = advertBannerDao.getBackAdvImgList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * @description 后台管理：新增广告图片
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject insertAdvImg(JSONObject jsonObject) {
        if (advertBannerDao.getAdvImg(jsonObject).size()==5){
            return CommonUtil.errorJson(ErrorEnum.E_30001);
        }
        advertBannerDao.insertAdvImg(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * @description 后台管理：逻辑删除广告栏图片
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject updateDelAdvImg(JSONObject jsonObject) {
        if(jsonObject.get("advId")==null||jsonObject.get("advId")=="")
        {
            return CommonUtil.errorJson(ErrorEnum.E_90003);
        }
        advertBannerDao.updateDelAdvImg(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * @description 后台管理：编辑广告栏图片信息
     * @param jsonObject
     * @return void
     **/
    @Override
    public JSONObject updateAdvImg(JSONObject jsonObject) {
        advertBannerDao.updateAdvImg(jsonObject);
        return CommonUtil.successJson();

    }

    /**
     * @description 对广告栏图片进行排序
     * @param jsonObject
     * @return void
     **/
    @Override
    public JSONObject sortAdvImgList(JSONObject jsonObject) {

        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(jsonObject.get("formerSortTime").toString() .equals(jsonObject.get("laterSortTime").toString())) {
            try {
                Date laterSortTime = sDateFormat.parse(jsonObject.get("laterSortTime").toString());
                long time = 1 * 1000;
                Date temp = new Date(laterSortTime.getTime() + time);
                jsonObject.put("laterSortTime", temp);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        advertBannerDao.sortAdvImgList(jsonObject);
        return CommonUtil.successJson();

    }
}
