package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface StatisticsDao {

    /**
     * 获取帖子地区列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostRegionList(JSONObject jsonObject);

    /**
     * 根据地区名称获取年度报告
     * 注意该查询使用 like 查询 - 并且没有使用索引，效率极低
     * @param jsonObject
     *        region 地区名称 必传
     * @return
     */
    JSONObject getAnnualReportByRegion(JSONObject jsonObject);

    /**
     * 最低价格计算折线图
     * @param jsonObject
     * type null = all
     *      week = nowWeekData
     *      month = nowNonthData
     * @return
     */
    List<JSONObject> getPriceLineData(JSONObject jsonObject);

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
     * @description 帖子收藏量总统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostCollectAll(JSONObject jsonObject);

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
     * @description 帖子评论总统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostCommentAll(JSONObject jsonObject);

    /**
     * @description 帖子点赞量总统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostLikeAll(JSONObject jsonObject);

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
     * @description 帖子地区浏览量按总统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getPostRegionAll(JSONObject jsonObject);

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

    /**
     * @description 用户关注数量总统计
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getUserFollowAll(JSONObject jsonObject );

}
