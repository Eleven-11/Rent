package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.StatisticsService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName StatisticsController
 * @Description 数据统计接口
 * @Author Lingling00
 * @DATE 8/2/2019 15:56
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    /**
     * @description 总性别统计
     * @param request
     * @return com.alibaba.fastjson.JSONObject
     **/
    @GetMapping("/genderByAll")
    public JSONObject genderByAllStatistic(HttpServletRequest request) {
        //统计用户性别，总性别统计
        return statisticsService.getGenderByAll(CommonUtil.request2Json(request));
    }

    /**
     * @description 月性别统计
     * @param request gender - 性别
     * @return com.alibaba.fastjson.JSONObject
     **/
    @GetMapping("/genderByMonth")
    public JSONObject genderByMonthStatistic(HttpServletRequest request) {
        //统计用户性别，月性别统计
        return statisticsService.getGenderByMonth(CommonUtil.request2Json(request));
    }

    @GetMapping("/postLikeAll")
    public JSONObject postLikeAllStatistic(HttpServletRequest request) {
        //统计周 帖子的点赞排名
        return statisticsService.getPostLikeAll(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postLikeByWeek")
    public JSONObject postLikeByWeekStatistic(HttpServletRequest request) {
        //统计周 帖子的点赞排名
        return statisticsService.getPostLikeByWeek(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postLikeByMonth")
    public JSONObject postLikeByMonthStatistic(HttpServletRequest request) {
        //统计月 帖子的点赞排名
        return statisticsService.getPostLikeByMonth(CommonUtil.request2Json(request));
    }

    @GetMapping("/postCommentByWeek")
    public JSONObject postCommentByWeekStatistic(HttpServletRequest request) {
        //统计周 帖子的评论排名
        return statisticsService.getPostCommentByWeek(CommonUtil.request2Json(request));
    }

    @GetMapping("/postCommentAll")
    public JSONObject postCommentAlltatistic(HttpServletRequest request) {
        //统计周 帖子的评论排名
        return statisticsService.getPostCommentAll(CommonUtil.request2Json(request));
    }

    @GetMapping("/postCommentByMonth")
    public JSONObject postCommentByMonthStatistic(HttpServletRequest request) {
        //统计月 帖子的评论排名
        return statisticsService.getPostCommentByMonth(CommonUtil.request2Json(request));
    }

    @GetMapping("/postCollectAll")
    public JSONObject postCollectAllStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return statisticsService.getPostCollectAll(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postCollectByWeek")
    public JSONObject postCollectByWeekStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return statisticsService.getPostCollectByWeek(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postCollectByMonth")
    public JSONObject postCollectByMonthStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return statisticsService.getPostCollectByMonth(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postMinPriceByWeek")
    public JSONObject postMinPriceByWeekStatistic(HttpServletRequest request) {
        //统计周最低价位统计折线图（5 - 10 - 15 - 20）
        return statisticsService.getPostMinPriceByWeek(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postMinPriceByMonth")
    public JSONObject postMinPriceByMonthStatistic(HttpServletRequest request) {
        //统计月最低价位统计折线图（5 - 10 - 15 - 20）
        return statisticsService.getPostMinPriceByMonth(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postMaxPriceByWeek")
    public JSONObject postMaxPriceByWeekStatistic(HttpServletRequest request) {
        //统计周最高价位统计折线图（5 - 10 - 15 - 20）
        return statisticsService.getPostMaxPriceByWeek(CommonUtil.request2Json(request));
    }

    @GetMapping("/postMaxPriceByMonth")
    public JSONObject postMaxPriceByMonthStatistic(HttpServletRequest request) {
        //统计月最低价位统计折线图（5 - 10 - 15 - 20）
        return statisticsService.getPostMaxPriceByMonth(CommonUtil.request2Json(request));
    }

    @GetMapping("/userFollowAll")
    public JSONObject userFollowAllStatistic(HttpServletRequest request) {
        //统计总 用户的关注排名
        return statisticsService.getUserFollowAll(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/userFollowByWeek")
    public JSONObject userFollowByWeekStatistic(HttpServletRequest request) {
        //统计周 用户的关注排名
        return statisticsService.getUserFollowByWeek(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/userFollowByMonth")
    public JSONObject userFollowByMonthStatistic(HttpServletRequest request) {
        //统计月 用户的关注排名
        return statisticsService.getUserFollowByMonth(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postRegionByWeek")
    public JSONObject postRegionByWeekStatistic(HttpServletRequest request) {
        // 地区帖子按周查看统计，根据帖子的真实浏览量做排名
        return statisticsService.getPostRegionByWeek(CommonUtil.request2Json(request));
    }
    
    @GetMapping("/postRegionByMonth")
    public JSONObject postRegionByMonthStatistic(HttpServletRequest request) {
        // 帖子地区按月查看统计，根据帖子的真实浏览量做排名；可查看其他月的数据，形成年报表
        return statisticsService.getPostRegionByMonth(CommonUtil.request2Json(request));
    }

    @GetMapping("/postRegionAll")
    public JSONObject getPostRegionAll(HttpServletRequest request) {
        // 地区帖子总统计，根据帖子的真实浏览量做排名
        return statisticsService.getPostRegionAll(CommonUtil.request2Json(request));
    }


    @GetMapping("/priceLineData")
    public JSONObject getAllPriceLineData(HttpServletRequest request) {
        // 帖子地区按月查看统计，根据帖子的真实浏览量做排名；可查看其他月的数据，形成年报表
        return statisticsService.getAllPriceLineData(CommonUtil.request2Json(request));
    }


}
