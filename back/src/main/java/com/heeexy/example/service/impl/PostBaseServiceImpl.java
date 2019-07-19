package com.heeexy.example.service.impl;

import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.util.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public JSONObject getPostBaseList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postBaseDao.countPost(jsonObject);
        List<com.alibaba.fastjson.JSONObject> list = postBaseDao.getPostBaseList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

}
