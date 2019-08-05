package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 用户关注表数据库操作
 * @date: 2019-07-23 17:55
 */
public interface UserFollowDao {

    /**
     * 根据userId查询用户关注列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getUserFollowList(JSONObject jsonObject);

    /**
     * 根据userId查询用户粉丝列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getUserFansList(JSONObject jsonObject);

    /**
     * 计算用户关注列表条数
     * @param jsonObject
     * @return
     */
    int countUserFollow(JSONObject jsonObject);

    /**
     * 计算用户粉丝列表条数
     * @param jsonObject
     * @return
     */
    int countUserFans(JSONObject jsonObject);

    /**
     * 添加用户关注信息
     * @param jsonObject
     * @return
     */
    int insertUserFollow(JSONObject jsonObject);

    /**
     * 更新用户关注信息
     * @param jsonObject
     * @return
     */
    int updateDelFollow(JSONObject jsonObject);

    /**
     * 判断是否关注过
     * @param jsonObject
     * @return
     */
    JSONObject getIfFollow(JSONObject jsonObject);


    /**
     * @description 获取用户关注状态
     * @param jsonObject
     * @return int
     **/
    int getFollowStatus(JSONObject jsonObject);
}
