package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.AdvertBannerDao;
import com.heeexy.example.service.AdverBannerService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @description 后台管理：新增广告图片
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject insertAdvImg(JSONObject jsonObject) {
        advertBannerDao.insertAdvImg(jsonObject);
        return CommonUtil.successJson(jsonObject.get("advId"));
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
        return CommonUtil.successJson("操作成功");
    }

    /**
     * @description 后台管理：编辑广告栏图片信息
     * @param jsonObject
     * @return void
     **/
    @Override
    public JSONObject updateAdvImg(JSONObject jsonObject) {
        advertBannerDao.updateAdvImg(jsonObject);
        return CommonUtil.successJson("操作成功！刷新后查看！");

    }
}
