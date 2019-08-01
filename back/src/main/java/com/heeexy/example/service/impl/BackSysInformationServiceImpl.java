package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.Const;
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
     * targetId   接收人ID  如果为system_info群发
     * content    发送的内容
     * 注意该接口未做targetId校验
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject sendMessage(JSONObject jsonObject) throws IOException {
        //判断是群发还是单发
        if (jsonObject.getString("targetId").equals("system_info")){
            sysInformationDao.insertInformation(jsonObject);
            WebSocketServer.sendMessageAll(jsonObject, Const.SEND_SYS_MESSAGE);
        } else {
            sysInformationDao.insertInformation(jsonObject);
            WebSocketServer.sendMessage(jsonObject, jsonObject.getString("targetId"), Const.SEND_SYS_MESSAGE);
        }
        return CommonUtil.successJson("发送成功");
    }
}
