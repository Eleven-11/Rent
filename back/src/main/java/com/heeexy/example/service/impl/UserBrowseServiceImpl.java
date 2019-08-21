package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.UserBrowseDao;
import com.heeexy.example.service.UserBrowseService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserBrowseServiceImpl
 * @Description 用户浏览相关操作
 * @Author Lingling00
 * @DATE 7/25/2019 09:35
 * @VERSION 1.0
 **/
@Service
public class UserBrowseServiceImpl implements UserBrowseService {
    @Autowired
    private UserBrowseDao userBrowseDao;
    @Autowired
    private PostBaseDao postBaseDao;
    /**
     * @description 获取用户浏览列表
     * @param jsonObject
     * @return
     **/
    @Override
    public JSONObject getUserBrowsePostList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> userBrowsePostIds =userBrowseDao.getUserBrowse(jsonObject);
        List<JSONObject> userBrowsePostList=new ArrayList<>();
        for (int i=0;i<userBrowsePostIds.size();i++){
            JSONObject userBrowsePost = postBaseDao.getWxUserPostInfo(userBrowsePostIds.get(i));
            //当浏览的帖子已删除或其余情况导致获取到空值，则不返回该条记录
            if(userBrowsePost!=null){
                userBrowsePostList.add(userBrowsePost);
            }
        }
        int count = userBrowsePostList.size();
        return CommonUtil.successPage(jsonObject,userBrowsePostList,count);
    }
    /**
     * @description 添加用户浏览信息并更新对应帖子的浏览量，若已浏览过则更新浏览时间
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized JSONObject insertUserBrowse(JSONObject jsonObject) {
            if(userBrowseDao.getBrowseStatus(jsonObject) != null){
                userBrowseDao.updateUserBrowse(jsonObject);
                return CommonUtil.successJson();
            }
            else {
                userBrowseDao.insertUserBrowse(jsonObject);
                return CommonUtil.successJson("插入成功");
            }
    }

    @Override
    public JSONObject getPostBrowseList(JSONObject jsonObject) {
        return CommonUtil.successJson(userBrowseDao.getPostBrowseList(jsonObject));
    }
}
