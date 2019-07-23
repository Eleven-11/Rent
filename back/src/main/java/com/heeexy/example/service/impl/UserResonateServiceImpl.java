package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.UserResonateDao;
import com.heeexy.example.service.UserResonateService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 获取帖子点赞列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostLikeList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        if (jsonObject.get("limit")!=null &&jsonObject.get("limit")!=""){
            List<JSONObject> list = userResonateDao.getPostLikeList(jsonObject);
            return CommonUtil.successPage(jsonObject, list, (Integer) jsonObject.get("limit"));
        }
        else {
            int count = userResonateDao.countPostList(jsonObject);
            List<JSONObject> list = userResonateDao.getPostLikeList(jsonObject);
            return CommonUtil.successPage(jsonObject, list, count);
        }
    }

    /**
     * 添加帖子点赞信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject insertPostLike(JSONObject jsonObject) {
        /*获取用户首次点赞帖子信息的时间*/
        Date postLikeCreateTime = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(postLikeCreateTime));
        jsonObject.put("postLikeCreateTime",postLikeCreateTime);
        userResonateDao.insertPostLike(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 取消点赞信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updateDelPostLike(JSONObject jsonObject) {
       userResonateDao.updateDelPostLike(jsonObject);
       return CommonUtil.successJson();
    }
}
