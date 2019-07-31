package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.websocket.WebSocketServer;
import com.heeexy.example.dao.SysInformationDao;
import com.heeexy.example.service.BackSysInformationService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: chens
 * @description: 后台系统消息业务逻辑实现
 * @date: 2019-07-31 14:13
 */
@Service
public class BackSysInformationServiceImpl implements BackSysInformationService {

    @Autowired
    private SysInformationDao sysInformationDao;

    /**
     * 发送系统消息
     * startId     发送人ID  rent系统默认
     * receiveId   接收人ID
     * content    发送的内容
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject sendMessage(JSONObject jsonObject) throws IOException {
        //判断是群发还是单发
        if (jsonObject.getString("receiveId").equals("rent")){
            WebSocketServer.sendMessageAll(jsonObject);
        } else {
            WebSocketServer.sendSysMessage(jsonObject,jsonObject.getString("receiveId"));
        }
        return CommonUtil.successJson("发送成功");
    }
}
