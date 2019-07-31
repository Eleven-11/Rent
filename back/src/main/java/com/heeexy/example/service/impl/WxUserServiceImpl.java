package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: Lingling
 * @description: 小程序用户业务逻辑实现
 * @date: 2019-07-23 17:48
 */
@Service
public class  WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

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
     * @description 新增微信授权用户信息
     * @param jsonObject
     * @return java.lang.String
     **/
    @Override
    public String insertWxUser(JSONObject jsonObject) {
        String userId = UUIDUtils.getUUID();
        jsonObject.put("userId",userId);
        wxUserDao.insertWxUser(jsonObject);
        return userId;
    }

    /**
     * @description 获取微信用户信息
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject getWxUserInfo(JSONObject jsonObject) {
        return wxUserDao.getWxUserInfo(jsonObject);
    }

    /**
     * @description 判断用户授权状态，若已授权则返回相应信息
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject getAuthStatus(JSONObject jsonObject) {
        return wxUserDao.getAuthStatus(jsonObject);
    }

    @Override
    public JSONObject updateWxUserSummary(JSONObject jsonObject) {
        wxUserDao.updateWxUserSummary(jsonObject);
        return CommonUtil.successJson(jsonObject.get("summary"));
    }

}
