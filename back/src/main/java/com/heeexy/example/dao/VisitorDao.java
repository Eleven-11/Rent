package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: Lingling
 * @description: 游客登陆数据库操作
 * @date: 2019-07-23 17:57
 */
public interface VisitorDao {

    /**
     * 校验是否有登陆信息
     * @param jsonObject
     * @return
     */
    JSONObject queryUser(JSONObject jsonObject);
    /**
     * 对于首次登录且未授权用户，在游客表生成信息并返回系统用户唯一识别的userId
     * @param jsonObject
     * @return
     */
    void insertVisitor(JSONObject jsonObject);
}
