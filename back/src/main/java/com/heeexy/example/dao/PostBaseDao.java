package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 帖子表数据库方法
 * @table t_post ? ?
 * @date: 2019-07-23 17:49
 */
public interface PostBaseDao {

    /**
     * 获取帖子列表，包括帖子的基本信息
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostBaseList(JSONObject jsonObject);

    /**
     * 获取模块icing的帖子列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getTopPostBaseList(JSONObject jsonObject);

    /**
     * 计算获取帖子列表的总条数（用于前端分页）
     * @param jsonObject
     * @return
     */
    int countPost(JSONObject jsonObject);

    /**
     * 更改帖子的点赞数量及浏览量
     * @param jsonObject
     */
    void updatePostBase(JSONObject jsonObject);

    /**
     * 发布帖子时插入帖子基本信息
     * @param jsonObject
     * @return
     */
    void insertPostBase(JSONObject jsonObject);


    //------------- 小程序相关操作 ----------------

    /**
     * 获取帖子列表，包括帖子的基本信息
     * @param jsonObject
     * @return
     */
    List<JSONObject> getWxPostBaseList(JSONObject jsonObject);

    /**
     * 小程序：获取用户点赞的帖子、评论的帖子、浏览过的帖子部分信息用于页面显示
     * @param jsonObject
     * @return
     **/
    JSONObject getWxUserPostInfo(JSONObject jsonObject);

    /**
     * 小程序：通过postId，判断帖子是否存在，isDel = 0
     * @param jsonObject
     * @return
     **/
    JSONObject getWxPostInfoByPostId(JSONObject jsonObject);
    /**
     * 小程序：判断用户发帖次数是否超过上限
     * @param
     * @return
     **/
    int getReleaseTime(JSONObject jsonObject);
    /**
     * @description 设置帖子上下架
     * @param jsonObject
     * @return int
     **/
    int updateOnShelf(JSONObject jsonObject);

    /**
     * @description 删除帖子（逻辑删除）
     * @param jsonObject
     * @return void
     **/
    void updateDelPost(JSONObject jsonObject);

    /**
     * @description 逻辑恢复已删除的帖子
     * @param jsonObject
     * @return void
     **/
    void updateDeletedPost(JSONObject jsonObject);


}
