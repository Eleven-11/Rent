package com.heeexy.example.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: chens
 * @description: 系统消息dao层方法定义
 * @date: 2019-07-31 14:22
 */
public interface SysInformationDao {

    /**
     * 获取系统消息
     * @param jsonObject
     * targetId       接收人ID
     * content        内容
     * createTime     创建时间
     * @return
     */
    List<JSONObject> getSysInforListByUserIdFlagTime(JSONObject jsonObject);

    /**
     * 插入系统消息数据
     *  targetId   目标ID，单发则是相应的用户ID，群发为system_info
     *  content    发送的文本内容
     *  createTime 创建时间
     * @param jsonObject
     * @return
     */
    Integer insertInformation(JSONObject jsonObject);
}
