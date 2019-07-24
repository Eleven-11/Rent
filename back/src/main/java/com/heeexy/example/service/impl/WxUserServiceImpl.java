package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.VisitorDao;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * @author: Lingling
 * @description: 小程序用户业务逻辑实现
 * @date: 2019-07-23 17:48
 */
@Service
public class  WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private VisitorDao visitorDao;


    /**
     * 功能描述:获取用户列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getWxUserList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = wxUserDao.countWxUser(jsonObject);
        List<JSONObject> list = wxUserDao.getWxUserList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject insertWxUser(JSONObject jsonObject) {
        String userId =UUID.randomUUID().toString();
        jsonObject.put("userId",userId);
        wxUserDao.insertWxUser(jsonObject);
        JSONObject returnJson = new JSONObject();
        returnJson.put("userId",userId);
        return returnJson;
    }


}
