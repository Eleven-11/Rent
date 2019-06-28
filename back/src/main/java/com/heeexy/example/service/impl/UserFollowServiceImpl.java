package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.UserFollowDao;
import com.heeexy.example.service.UserFollowService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingling
 * @description 用户关注列表相关操作
 * @date 2019/6/27 0027
 */
@Service
public class UserFollowServiceImpl implements UserFollowService {
    @Autowired
    private UserFollowDao userFollowDao;
    /*
     *功能描述:根据用户ID查询用户关注列表
     */
    @Override
    public JSONObject getUserFollowList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userFollowDao.countUserFollow(jsonObject);
        List<JSONObject> list = userFollowDao.getUserFollowList(jsonObject);
        System.out.println(list);
        return CommonUtil.successPage(jsonObject, list, count);
    }
    /*
     *功能描述:根据用户ID查询用户粉丝列表
     */
    @Override
    public JSONObject getUserFansList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userFollowDao.countUserFans(jsonObject);
        List<JSONObject> list = userFollowDao.getUserFansList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
}
