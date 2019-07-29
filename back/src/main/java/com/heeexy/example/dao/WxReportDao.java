package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: chens
 * @description: 用户举报的dao接口
 * @date: 2019-07-29 18:08
 */
public interface WxReportDao {

    /**
     * 获取用户当天已举报次数
     * userId 用户Id
     * today 日期
     * @param jsonObject
     * @return
     */
    Integer countUserReportToday(JSONObject jsonObject);

    /**
     * 校验用户是否举报过
     * userId 用户Id
     * targetType 举报对象的类型
     * targetId 举报对象的Id
     * @param jsonObject
     * @return
     */
    JSONObject queryReportByUserId(JSONObject jsonObject);

    /**
     * 用户举报
     * @param jsonObject
     * @return
     */
    Integer insertReport(JSONObject jsonObject);
}
