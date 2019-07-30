package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: chens
 * @description: 用户消息dao接口定义
 * @date: 2019-07-29 20:03
 */
public interface WxUserInformationDao {


    /**
     * 获取用户最新消息
     * userId 用户id
     * flagTime 参考时间
     * @param jsonObject
     * @return
     *          startAvatarUrl
     *          startNickName
     *          startGender
     *          startId
     *          content
     *          createTime
     */
    List<JSONObject> getNewInformationByUserIdFlagTime(JSONObject jsonObject);

    //---- websocket相关dao -----

    /**
     * 插入消息
     * @param jsonObject
     * @return
     */
    Integer insertInformation(JSONObject jsonObject);
}
