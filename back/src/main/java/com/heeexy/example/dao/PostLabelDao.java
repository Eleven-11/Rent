package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 帖子标签表数据库方法
 * @date: 2019-07-23 17:51
 */
public interface PostLabelDao {

    /**
     * 获取帖子标签列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostLabelList(JSONObject jsonObject);

    /**
     * 添加帖子标签
     * @param jsonObject
     * @return
     */
    void insertPostLabel(JSONObject jsonObject);

    /**
     * 更新帖子标签
     * @param jsonObject
     */
    void updatePostLabel(JSONObject jsonObject);

    /**
     * 删除帖子标签
     * @param jsonObject
     */
    void updateDelPostLabel(JSONObject jsonObject);

    /**
     * 帖子标签
     * @param jsonObject
     * @return
     */
    int countPostLabel(JSONObject jsonObject);

    /**
     * 获取帖子标签列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getLabelByContent(JSONObject jsonObject);

    /**
     * @description 发布帖子添加对应帖子标签记录
     * @param
     * @return void
     **/
    void insertLabel(JSONObject jsonObject);

    /**
     * @description 用户更新帖子时先删除原有的帖子标签记录
     * @param
     * @return void
     **/
    void deleteLabel(JSONObject jsonObject);

    /**
     * @description 获取指定帖子的标签列表
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostLabelById(JSONObject jsonObject);
}
