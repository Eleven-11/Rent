package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface StatisticsDao {

    /**
     * @description
     * @param
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<JSONObject> getGenderByAll(JSONObject jsonObject);

    List<JSONObject> getPostPriceByMonth(JSONObject jsonObject);

    List<JSONObject> getGenderByMonth(JSONObject jsonObject);

    List<JSONObject> getPostCommentByMonth(JSONObject jsonObject);

    List<JSONObject> getPostLikeByMonth(JSONObject jsonObject);

    List<JSONObject> getPostPriceByWeek(JSONObject jsonObject);

    List<JSONObject> getPostCollectByWeek(JSONObject jsonObject);

    List<JSONObject> getPostCollectByMonth(JSONObject jsonObject);

    List<JSONObject> getPostCommentByWeek(JSONObject jsonObject);

    List<JSONObject> getPostLikeByWeek(JSONObject jsonObject);

    List<JSONObject> getPostRegionByMonth(JSONObject jsonObject);

    List<JSONObject> getPostRegionByWeek(JSONObject jsonObject);

    List<JSONObject> getUserFollowByMonth(JSONObject jsonObject);

    List<JSONObject> getUserFollowByWeek(JSONObject jsonObject );
}
