package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserResonateDao {
    /**
     * @description 获取点赞列表条数用于前端分页
     **/
    int countPostList(JSONObject jsonObject);
    /**
     * @description 获取点赞列表(包括点赞人昵称及其头像)
     **/
    List<JSONObject> getPostLikeList(JSONObject jsonObject);
    /**
     * @description 添加点赞信息并返回主键
     **/
    int insertPostLike(JSONObject jsonObject);
    /**
     * @description 取消点赞
     **/
    int updateDelPostLike(JSONObject jsonObject);
}
