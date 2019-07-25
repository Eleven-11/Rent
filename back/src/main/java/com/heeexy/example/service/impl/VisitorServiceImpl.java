package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.VisitorDao;
import com.heeexy.example.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName VisitorServiceImpl
 * @Description 小程序游客用户相关操作
 * @Author Lingling00
 * @DATE 7/24/2019 11:09
 * @VERSION 1.0
 **/
@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorDao visitorDao;

    /**
     * 未授权用户后台生成游客信息
     * @param jsonObject
     * @return
     */
    @Override
    public String insertVisitor(JSONObject jsonObject) {
        String visitorId = UUID.randomUUID().toString();
        jsonObject.put("visitorId",visitorId);
        visitorDao.insertVisitor(jsonObject);
        return visitorId;
    }
}
