package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 用户收藏表数据库方法
 * @date: 2019-07-23 17:53
 */
public interface UserCollectionDao {

    /**
     * 根据userId查询用户收藏列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getUserCollList(JSONObject jsonObject);

    /**
     * 对用户收藏的帖子列表进行排序
     * @param jsonObject
     * @return
     */
    int sortUserColl(JSONObject jsonObject);

    /**
     * 计算用户收藏的帖子数量
     * @param jsonObject
     * @return
     */
    int countCollection(JSONObject jsonObject);

    /**
     * 添加用户收藏信息
     * @param jsonObject
     * @return
     */
    int insertUserCollection(JSONObject jsonObject);

    /**
     * 判断用户是否收藏过指定帖子（用于更新收藏状态，因此包含已取消收藏状态的判断）
     * @param jsonObject
     * @return
     */
    JSONObject getIfCollected(JSONObject jsonObject);
    /**
     * 判断用户是否收藏指定帖子
     * @param jsonObject
     * @return
     */
    JSONObject getIfCollect(JSONObject jsonObject);

    /**
     * 取消收藏
     * @param jsonObject
     * @return
     */
    int updateDelCollect(JSONObject jsonObject);

    /**
     * @description 获取用户当前收藏状态
     * @param jsonObject
     * @return int
     **/
    int getCollectStatus(JSONObject jsonObject);
}
