package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.StatisticsDao;
import com.heeexy.example.service.StatisticsService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StatisticsServiceImpl
 * @Description
 * @Author Lingling00
 * @DATE 8/5/2019 11:36
 * @VERSION 1.0
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public JSONObject getGenderByAll(JSONObject jsonObject) {
        System.out.println(statisticsDao.getGenderByAll(jsonObject));
        return CommonUtil.successJson(statisticsDao.getGenderByAll(jsonObject));
    }

    @Override
    public JSONObject getGenderByMonth(JSONObject jsonObject) {
        jsonObject.put("gender",1);
        List<JSONObject> manList = statisticsDao.getGenderByMonth(jsonObject);
        jsonObject.put("gender",2);
        List<JSONObject> womenList = statisticsDao.getGenderByMonth(jsonObject);
        JSONObject jo = new JSONObject();
        jo.put("women", womenList);
        jo.put("man", manList);
        return CommonUtil.successJson(jo);
    }

    @Override
    public JSONObject getPostMinPriceByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostMinPriceByMonth(jsonObject));
    }

    @Override
    public JSONObject getPostMinPriceByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostMinPriceByWeek(jsonObject));
    }

    @Override
    public JSONObject getPostMaxPriceByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostMaxPriceByMonth(jsonObject));
    }

    @Override
    public JSONObject getPostMaxPriceByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostMaxPriceByWeek(jsonObject));
    }

    @Override
    public JSONObject getPostCommentByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostCommentByMonth(jsonObject));
    }

    @Override
    public JSONObject getPostLikeByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostLikeByMonth(jsonObject));
    }

    @Override
    public JSONObject getPostCollectAll(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostCollectAll(jsonObject));
    }

    @Override
    public JSONObject getPostCollectByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostCollectByWeek(jsonObject));
    }

    @Override
    public JSONObject getPostCollectByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostCollectByMonth(jsonObject));
    }

    @Override
    public JSONObject getPostCommentByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostCommentByWeek(jsonObject));
    }

    @Override
    public JSONObject getPostCommentAll(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostCommentAll(jsonObject));
    }


    @Override
    public JSONObject getPostLikeAll(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostLikeAll(jsonObject));
    }

    @Override
    public JSONObject getPostLikeByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostLikeByWeek(jsonObject));
    }

    @Override
    public JSONObject getPostRegionByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostRegionByMonth(jsonObject));
    }

    @Override
    public JSONObject getPostRegionByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostRegionByWeek(jsonObject));
    }

    @Override
    public JSONObject getPostRegionAll(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getPostRegionAll(jsonObject));
    }


    @Override
    public JSONObject getUserFollowByMonth(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getUserFollowByMonth(jsonObject));
    }

    @Override
    public JSONObject getUserFollowByWeek(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getUserFollowByWeek(jsonObject));
    }

    @Override
    public JSONObject getUserFollowAll(JSONObject jsonObject) {
        return CommonUtil.successJson(statisticsDao.getUserFollowAll(jsonObject));
    }

    @Override
    public JSONObject getAllPriceLineData(JSONObject jsonObject) {
//        List<JSONObject> allList = statisticsDao.getPriceLineData(jsonObject);
//        jsonObject.put("type","week");
//        List<JSONObject> weekList = statisticsDao.getPriceLineData(jsonObject);
//        jsonObject.put("type","month");
//        List<JSONObject> monthList = statisticsDao.getPriceLineData(jsonObject);
//        jsonObject.put("allList",allList);
//        jsonObject.put("weekList",weekList);
//        jsonObject.put("monthList",monthList);
        List<JSONObject> list = statisticsDao.getPriceLineData(jsonObject);
        return CommonUtil.successPage(list);
    }
}
