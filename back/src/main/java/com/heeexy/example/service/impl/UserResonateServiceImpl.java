package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.UserCollectionDao;
import com.heeexy.example.dao.UserResonateDao;
import com.heeexy.example.service.UserResonateService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserResonateServiceImpl
 * @Description 用户点赞相关操作
 * @Author Lingling00
 * @DATE 7/15/2019 13:56
 * @VERSION 1.0
 **/
@Service
public class UserResonateServiceImpl implements UserResonateService {
    @Autowired
    private UserResonateDao userResonateDao;
    /*获取帖子点赞列表*/
    @Override
    public JSONObject getPostLikeList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userResonateDao.countPostList(jsonObject);
        List<JSONObject> list = userResonateDao.getPostLikeList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /*添加帖子点赞信息*/
    @Override
    public JSONObject insertPostLike(JSONObject jsonObject) {
        userResonateDao.insertPostLike(jsonObject);
        return CommonUtil.successJson();
    }

    /*取消点赞信息*/
    @Override
    public JSONObject updateDelPostLike(JSONObject jsonObject) {
       userResonateDao.updateDelPostLike(jsonObject);
       return CommonUtil.successJson();
    }
}
