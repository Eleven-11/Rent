package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface StatisticsService {
    /**
     * @description 性别统计 总
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getGenderByAll(JSONObject jsonObject);

    /**
     * @description 性别按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getGenderByMonth(JSONObject jsonObject);

    /**
     * @description 帖子最低价按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostMinPriceByMonth(JSONObject jsonObject);

    /**
     * @description 帖子最低价按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostMinPriceByWeek(JSONObject jsonObject);


    /**
     * @description 帖子最高价按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostMaxPriceByMonth(JSONObject jsonObject);

    /**
     * @description 帖子最高价按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostMaxPriceByWeek(JSONObject jsonObject);

    /**
     * @description 评论数量按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostCommentByMonth(JSONObject jsonObject);

    /**
     * @description 点赞数量按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostLikeByMonth(JSONObject jsonObject);

    /**
     * @description 帖子收藏量总统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostCollectAll(JSONObject jsonObject);

    /**
     * @description 帖子收藏量按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostCollectByWeek(JSONObject jsonObject);

    /**
     * @description 帖子收藏量按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostCollectByMonth(JSONObject jsonObject);

    /**
     * @description 帖子评论按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostCommentByWeek(JSONObject jsonObject);

    /**
     * @description 帖子评论按总统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostCommentAll(JSONObject jsonObject);

    /**
     * @description 帖子点赞量总统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostLikeAll(JSONObject jsonObject);

    /**
     * @description 帖子点赞量按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostLikeByWeek(JSONObject jsonObject);

    /**
     * @description 帖子地区浏览量按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostRegionByMonth(JSONObject jsonObject);

    /**
     * @description 帖子地区浏览量按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostRegionByWeek(JSONObject jsonObject);

    /**
     * @description 帖子地区浏览量按总统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getPostRegionAll(JSONObject jsonObject);

    /**
     * @description 用户关注数量按月统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getUserFollowByMonth(JSONObject jsonObject);

    /**
     * @description 用户关注数量总统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getUserFollowAll(JSONObject jsonObject);

    /**
     * @description 用户关注数量按周统计
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getUserFollowByWeek(JSONObject jsonObject);

    /**
     * 获取价格的所有折线数据
     * @param jsonObject
     * @return
     */
    JSONObject getAllPriceLineData(JSONObject jsonObject);

    /**
     * 通过地名获取年度报表
     * @param jsonObject
     * @return
     */
    JSONObject getAnnualReportByRegion(JSONObject jsonObject);

}
