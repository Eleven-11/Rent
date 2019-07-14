package com.heeexy.example.dao;

import com.heeexy.example.util.JSONResult;
import com.alibaba.fastjson.JSONObject;

public interface VisitorDao {
    /**
     * @description 对于首次登录且未授权用户，在游客表生成信息并返回系统用户唯一识别的userId
     **/
    String insertVisitor(JSONObject jsonObject);
}
