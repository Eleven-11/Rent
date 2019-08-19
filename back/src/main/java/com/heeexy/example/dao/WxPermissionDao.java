package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface WxPermissionDao {

    /**
     * 获取微信权限列表
     * type 为null时，获取全部权限
     * @param type
     * @return
     */
    List<String> listByType(Integer type);
}
