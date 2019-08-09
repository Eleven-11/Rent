package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 帖子图片表数据库方法
 * @date: 2019-07-23 17:51
 */
public interface PostImgDao {

    /**
     * 获取帖子图片列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostImgList(JSONObject jsonObject);

    /**
     * 插入帖子图片列表
     * @param
     * @return
     */
    Integer insertPostImgList(JSONObject postImgList);

    /**
     * @description 删除帖子图片（用户编辑已发布的帖子内容时触发）
     * @param jsonObject
     * @return void
     **/
    void deletePostImg(JSONObject jsonObject);
}
