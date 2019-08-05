package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface StatisticsDao {

    /**
     * @description 性别统计 总
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getGenderByAll(JSONObject jsonObject);

    /**
     * @description 性别按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getGenderByMonth(JSONObject jsonObject);

    /**
     * @description 帖子最低价按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostMinPriceByMonth(JSONObject jsonObject);

    /**
     * @description 帖子最低价按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostMinPriceByWeek(JSONObject jsonObject);


    /**
     * @description 帖子最高价按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostMaxPriceByMonth(JSONObject jsonObject);

    /**
     * @description 帖子最高价按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostMaxPriceByWeek(JSONObject jsonObject);

    /**
     * @description 评论数量按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostCommentByMonth(JSONObject jsonObject);

    /**
     * @description 点赞数量按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostLikeByMonth(JSONObject jsonObject);

    /**
     * @description 帖子收藏量按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostCollectByWeek(JSONObject jsonObject);

    /**
     * @description 帖子收藏量按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostCollectByMonth(JSONObject jsonObject);

    /**
     * @description 帖子评论按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostCommentByWeek(JSONObject jsonObject);

    /**
     * @description 帖子点赞量按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostLikeByWeek(JSONObject jsonObject);

    /**
     * @description 帖子地区浏览量按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostRegionByMonth(JSONObject jsonObject);

    /**
     * @description 帖子地区浏览量按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostRegionByWeek(JSONObject jsonObject);

    /**
     * @description 用户关注数量按月统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getUserFollowByMonth(JSONObject jsonObject);

    /**
     * @description 用户关注数量按周统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getUserFollowByWeek(JSONObject jsonObject );
}
