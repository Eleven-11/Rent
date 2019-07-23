package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.PostImgDao;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName PostBaseServiceImpl
 * @Description 帖子基本表相关操作
 * @Author Lingling00
 * @DATE 7/14/2019 14:39
 * @VERSION 1.0
 **/
@Service
public class PostBaseServiceImpl implements PostBaseService {
    @Autowired
    private PostBaseDao postBaseDao;
    @Autowired
    private PostImgDao postImgDao;


    /**
     * 获取帖子基本信息列表List
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostBaseList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postBaseDao.countPost(jsonObject);
        List<JSONObject> list = postBaseDao.getPostBaseList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 更改帖子点赞量、浏览量
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updatePostBase(JSONObject jsonObject) {
        if((jsonObject.get("devLike")==null||jsonObject.get("devLike")=="")&&(jsonObject.get("devBrowse")==null||jsonObject.get("devBrowse")=="")){
            return CommonUtil.errorJson(ErrorEnum.E_90003);
        }
        else
        {
            postBaseDao.updatePostBase(jsonObject);
            return CommonUtil.successJson("更新成功，刷新后查看");
        }
    }

    /**
     * 发布帖子
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject insertPostBase(JSONObject jsonObject) {
        String postId =UUID.randomUUID().toString();
        jsonObject.put("postId", postId);
        postBaseDao.insertPostBase(jsonObject);
        postImgDao.insertPostImgList(jsonObject);
        return CommonUtil.successJson("发布成功！");
    }


}
