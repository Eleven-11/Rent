package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.SysInformationDao;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.dao.WxUserInformationDao;
import com.heeexy.example.service.WxInformationService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chens
 * @description: 微信用户消息业务逻辑实现
 * @date: 2019-07-30 14:00
 */
@Service
public class WxInformationServiceImpl implements WxInformationService {


    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private WxUserInformationDao wxUserInformationDao;

    @Autowired
    private SysInformationDao sysInformationDao;

    /**
     * 获取用户最新未读消息
     * userId   用户ID
     * flagTime 参考时间
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getNewInfomation(JSONObject jsonObject) {
//        if (wxUserDao.queryUser(jsonObject) == null){
//            return
//        }
        //获取到最新的消息数据
        List<JSONObject> joList = wxUserInformationDao.getNewInformationByUserIdFlagTime(jsonObject);
        return CommonUtil.successPage(joList);
    }

    /**
     * 获取系统消息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getSysInformation(JSONObject jsonObject) {
        List<JSONObject> joList = sysInformationDao.getSysInforListByUserIdFlagTime(jsonObject);
        return CommonUtil.successPage(joList);
    }
}
