package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserBrowseDao {
    /**
     * @description 用户浏览过的帖子id列表
     * @param
     * @return
     **/
    List<JSONObject> getUserBrowse(JSONObject jsonObject);
    /**
     * @description 添加用户浏览信息
     * @param
     * @return
     **/
    @Transactional(readOnly = false)
    void insertUserBrowse (JSONObject jsonObject)throws DuplicateKeyException;

    /**
     * @description 获取用户浏览状态
     * @param jsonObject
     * @return int
     **/
    JSONObject getBrowseStatus(JSONObject jsonObject);

    /**
     * @description 更新用户浏览时间
     * @param
     * @return
     **/
    void updateUserBrowse(JSONObject jsonObject);

    /**
     * @description 获取浏览过指定帖子的用户列表
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostBrowseList(JSONObject jsonObject);
}
