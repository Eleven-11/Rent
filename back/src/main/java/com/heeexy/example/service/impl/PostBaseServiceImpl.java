package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingling
 * @description 帖子基本信息相关操作
 * @date 2019/7/9 0009
 */
@Service
public class PostBaseServiceImpl implements PostBaseService {
    @Autowired
    private PostBaseDao postBaseDao;

    @Override
    public JSONObject getPostList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postBaseDao.countPost(jsonObject);
        List<JSONObject> list = postBaseDao.getPostList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
}
