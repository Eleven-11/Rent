package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.VisitorDao;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 未授权用户后台生成游客信息
     * @param jsonObject
     * @return
     */
    @Override
    public String insertVisitor(JSONObject jsonObject) {
        Date createTime = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonObject.put("createTime",dateFormat.format(createTime));
        String visitorId =UUID.randomUUID().toString();
        jsonObject.put("visitorId",visitorId);
        visitorDao.insertVisitor(jsonObject);
        return visitorId;
    }


}
