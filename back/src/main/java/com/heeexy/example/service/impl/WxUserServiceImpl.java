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
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WxUserDao wxUserDao;


    @Override
    public JSONObject getWxUserList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject.toJSONString());
        int count = wxUserDao.countWxUser(jsonObject);
        List<JSONObject> list = wxUserDao.getWxUserList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
}
