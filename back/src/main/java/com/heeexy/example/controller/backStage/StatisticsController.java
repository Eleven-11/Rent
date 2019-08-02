package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
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

    /*TODO*/
    @GetMapping("/genderByAll")
    public JSONObject genderByAllStatistic(HttpServletRequest request) {
        //统计用户性别，总性别统计
        return null;
    }
    /*TODO*/
    @GetMapping("/genderByMonth")
    public JSONObject genderByMonthStatistic(HttpServletRequest request) {
        //统计用户性别，月性别统计
        return null;
    }
    /*TODO*/
    @GetMapping("/postLikeByWeek")
    public JSONObject postLikeByWeekStatistic(HttpServletRequest request) {
        //统计周 帖子的点赞排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postLikeByMonth")
    public JSONObject postLikeByMonthStatistic(HttpServletRequest request) {
        //统计月 帖子的点赞排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postCommentByWeek")
    public JSONObject postCommentByWeekStatistic(HttpServletRequest request) {
        //统计周 帖子的评论排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postCommentByMonth")
    public JSONObject postCommentByMonthStatistic(HttpServletRequest request) {
        //统计月 帖子的评论排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postCollectByWeek")
    public JSONObject postCollectByWeekStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postCollectByMonth")
    public JSONObject postCollectByMonthStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postPriceByWeek")
    public JSONObject postPriceByWeekStatistic(HttpServletRequest request) {
        //统计周价位统计折线图（5 - 10 - 15 - 20）
        return null;
    }
    /*TODO*/
    @GetMapping("/postPriceByMonth")
    public JSONObject postPriceByMonthStatistic(HttpServletRequest request) {
        //统计月 价位统计折线图（5 - 10 - 15 - 20）
        return null;
    }
    /*TODO*/
    @GetMapping("/userFollowByWeek")
    public JSONObject userFollowByWeekStatistic(HttpServletRequest request) {
        //统计周 用户的关注排名
        return null;
    }
    /*TODO*/
    @GetMapping("/userFollowByMonth")
    public JSONObject userFollowByMonthStatistic(HttpServletRequest request) {
        //统计月 用户的关注排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postRegionByWeek")
    public JSONObject postRegionByWeekStatistic(HttpServletRequest request) {
        // 地区帖子按周查看统计，根据帖子的真实浏览量做排名
        return null;
    }
    /*TODO*/
    @GetMapping("/postRegionByMonth")
    public JSONObject postRegionByMonthStatistic(HttpServletRequest request) {
        // 帖子地区按月查看统计，根据帖子的真实浏览量做排名；可查看其他月的数据，形成年报表
        return null;
    }

}
