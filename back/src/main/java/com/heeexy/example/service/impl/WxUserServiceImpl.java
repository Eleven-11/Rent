package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WxUserDao wxUserDao;

    /*
     *功能描述:获取用户列表
     */
    @Override
    public JSONObject getWxUserList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = wxUserDao.countWxUser(jsonObject);
        List<JSONObject> list = wxUserDao.getWxUserList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     *@description:未授权用户后台生成游客信息
     */
    @Override
    public String insertVisitor(JSONObject jsonObject) {
        Date createTime = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        jsonObject.put("createTime",dateFormat.format(createTime));
        String visitorId =UUID.randomUUID().toString();
        jsonObject.put("visitorId",visitorId);
        wxUserDao.insertVisitor(jsonObject);
        return visitorId;
    }


}
