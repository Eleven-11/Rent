package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 帖子类型表数据库方法
 * @date: 2019-07-23 17:52
 */
public interface PostTypeDao {

    /**
     * 获取帖子类型列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostTypeList(JSONObject jsonObject);

    /**
     * 后台：获取帖子类型列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getBackPostTypeList(JSONObject jsonObject);

    /**
     * 后台：添加帖子类型信息
     * @param jsonObject
     * @return
     */
    int countBackPostType(JSONObject jsonObject);

    /**
     * 根据帖子类型id修改相应信息
     * @param jsonObject
     */
    void updatePostTypeById(JSONObject jsonObject);

    /**
     * 根据帖子类型id逻辑删除相应信息
     * @param jsonObject
     */
    void updateDelPostTypeById(JSONObject jsonObject);

    /**
     * 计算列表条数
     * @param jsonObject
     * @return
     */
    int countPostType(JSONObject jsonObject);

    /**
     * 添加帖子类型信息
     * @param jsonObject
     * @return
     */
    int insertPostType(JSONObject jsonObject);

    /**
     * @description 帖子八大类型排序
     * @param jsonObject
     * @return void
     **/
    void sortPostType(JSONObject jsonObject);
}
