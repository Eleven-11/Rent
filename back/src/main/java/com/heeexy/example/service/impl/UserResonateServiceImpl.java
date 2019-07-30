package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.UserResonateDao;
import com.heeexy.example.service.UserResonateService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private PostBaseDao postBaseDao;

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
     * 取消点赞信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updatePostLike(JSONObject jsonObject) {
        if(userResonateDao.getIfLiked(jsonObject)!=null){
            userResonateDao.updateDelPostLike(jsonObject);
            return CommonUtil.successJson();
        }
        else{
            userResonateDao.insertPostLike(jsonObject);
            return CommonUtil.successJson();
        }
    }
    /**
     * @description 获取用户点赞列表
     * @param jsonObject
     * @return
     **/
    @Override
    public JSONObject getUserLikePostList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> userLikePostIds =userResonateDao.getUserLikeList(jsonObject);
        List<JSONObject> userLikePostList=new ArrayList<>();
        for (int i=0;i<userLikePostIds.size();i++){
            JSONObject userLikePost = postBaseDao.getWxUserPostInfo(userLikePostIds.get(i));
            userLikePostList.add(userLikePost);
        }
        int count = userLikePostIds.size();
        return CommonUtil.successPage(jsonObject,userLikePostList,count);
    }
}
