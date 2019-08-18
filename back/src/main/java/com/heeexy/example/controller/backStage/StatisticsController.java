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

    /**
     * 帖子点赞总统计
     * @param request
     * @return
     */
    @GetMapping("/postLikeAll")
    public JSONObject postLikeAllStatistic(HttpServletRequest request) {
        //统计周 帖子的点赞排名
        return statisticsService.getPostLikeAll(CommonUtil.request2Json(request));
    }

    /**
     * 帖子点赞周统计
     * @param request
     * @return
     */
    @GetMapping("/postLikeByWeek")
    public JSONObject postLikeByWeekStatistic(HttpServletRequest request) {
        //统计周 帖子的点赞排名
        return statisticsService.getPostLikeByWeek(CommonUtil.request2Json(request));
    }

    /**
     * 帖子点赞月统计
     * @param request
     * @return
     */
    @GetMapping("/postLikeByMonth")
    public JSONObject postLikeByMonthStatistic(HttpServletRequest request) {
        //统计月 帖子的点赞排名
        return statisticsService.getPostLikeByMonth(CommonUtil.request2Json(request));
    }

    /**
     * 帖子评论总统计
     * @param request
     * @return
     */
    @GetMapping("/postCommentAll")
    public JSONObject postCommentAlltatistic(HttpServletRequest request) {
        //统计周 帖子的评论排名
        return statisticsService.getPostCommentAll(CommonUtil.request2Json(request));
    }

    /**
     * 帖子评论周统计
     * @param request
     * @return
     */
    @GetMapping("/postCommentByWeek")
    public JSONObject postCommentByWeekStatistic(HttpServletRequest request) {
        //统计周 帖子的评论排名
        return statisticsService.getPostCommentByWeek(CommonUtil.request2Json(request));
    }

    /**
     * 帖子评论月统计
     * @param request
     * @return
     */
    @GetMapping("/postCommentByMonth")
    public JSONObject postCommentByMonthStatistic(HttpServletRequest request) {
        //统计月 帖子的评论排名
        return statisticsService.getPostCommentByMonth(CommonUtil.request2Json(request));
    }

    /**
     * 帖子收藏总统计
     * @param request
     * @return
     */
    @GetMapping("/postCollectAll")
    public JSONObject postCollectAllStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return statisticsService.getPostCollectAll(CommonUtil.request2Json(request));
    }

    /**
     * 帖子收藏周统计
     * @param request
     * @return
     */
    @GetMapping("/postCollectByWeek")
    public JSONObject postCollectByWeekStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return statisticsService.getPostCollectByWeek(CommonUtil.request2Json(request));
    }

    /**
     * 帖子收藏月统计
     * @param request
     * @return
     */
    @GetMapping("/postCollectByMonth")
    public JSONObject postCollectByMonthStatistic(HttpServletRequest request) {
        //统计周/月 帖子的收藏排名
        return statisticsService.getPostCollectByMonth(CommonUtil.request2Json(request));
    }

    /**
     * 价格折线图数据
     * @param request
     * @return
     */
    @GetMapping("/priceLineData")
    public JSONObject getAllPriceLineData(HttpServletRequest request) {
        // 帖子地区按月查看统计，根据帖子的真实浏览量做排名；可查看其他月的数据，形成年报表
        return statisticsService.getAllPriceLineData(CommonUtil.request2Json(request));
    }

//
//    @GetMapping("/postMinPriceByWeek")
//    public JSONObject postMinPriceByWeekStatistic(HttpServletRequest request) {
//        //统计周最低价位统计折线图（5 - 10 - 15 - 20）
//        return statisticsService.getPostMinPriceByWeek(CommonUtil.request2Json(request));
//    }
//
//    @GetMapping("/postMinPriceByMonth")
//    public JSONObject postMinPriceByMonthStatistic(HttpServletRequest request) {
//        //统计月最低价位统计折线图（5 - 10 - 15 - 20）
//        return statisticsService.getPostMinPriceByMonth(CommonUtil.request2Json(request));
//    }
//
//    @GetMapping("/postMaxPriceByWeek")
//    public JSONObject postMaxPriceByWeekStatistic(HttpServletRequest request) {
//        //统计周最高价位统计折线图（5 - 10 - 15 - 20）
//        return statisticsService.getPostMaxPriceByWeek(CommonUtil.request2Json(request));
//    }
//
//    @GetMapping("/postMaxPriceByMonth")
//    public JSONObject postMaxPriceByMonthStatistic(HttpServletRequest request) {
//        //统计月最低价位统计折线图（5 - 10 - 15 - 20）
//        return statisticsService.getPostMaxPriceByMonth(CommonUtil.request2Json(request));
//    }

    /**
     * 用户关注总统计
     * @param request
     * @return
     */
    @GetMapping("/userFollowAll")
    public JSONObject userFollowAllStatistic(HttpServletRequest request) {
        //统计总 用户的关注排名
        return statisticsService.getUserFollowAll(CommonUtil.request2Json(request));
    }

    /**
     * 用户关注周统计
     * @param request
     * @return
     */
    @GetMapping("/userFollowByWeek")
    public JSONObject userFollowByWeekStatistic(HttpServletRequest request) {
        //统计周 用户的关注排名
        return statisticsService.getUserFollowByWeek(CommonUtil.request2Json(request));
    }

    /**
     * 用户关注月统计
     * @param request
     * @return
     */
    @GetMapping("/userFollowByMonth")
    public JSONObject userFollowByMonthStatistic(HttpServletRequest request) {
        //统计月 用户的关注排名
        return statisticsService.getUserFollowByMonth(CommonUtil.request2Json(request));
    }

    /**
     * 贴子地区总统计
     * @param request
     * @return
     */
    @GetMapping("/postRegionAll")
    public JSONObject getPostRegionAll(HttpServletRequest request) {
        // 地区帖子总统计，根据帖子的真实浏览量做排名
        return statisticsService.getPostRegionAll(CommonUtil.request2Json(request));
    }

    /**
     * 贴子地区周统计
     * @param request
     * @return
     */
    @GetMapping("/postRegionByWeek")
    public JSONObject postRegionByWeekStatistic(HttpServletRequest request) {
        // 地区帖子按周查看统计，根据帖子的真实浏览量做排名
        return statisticsService.getPostRegionByWeek(CommonUtil.request2Json(request));
    }

    /**
     * 贴子地区月统计
     * @param request
     * @return
     */
    @GetMapping("/postRegionByMonth")
    public JSONObject postRegionByMonthStatistic(HttpServletRequest request) {
        // 帖子地区按月查看统计，根据帖子的真实浏览量做排名；可查看其他月的数据，形成年报表
        return statisticsService.getPostRegionByMonth(CommonUtil.request2Json(request));
    }

    /**
     * 地区年报数据
     * @param request
     * @return
     */
    @GetMapping("/annualReportByRegion")
    public JSONObject getAnnualReportByRegion(HttpServletRequest request) {
        // 地区年报表，效率极低，谨慎使用
        return statisticsService.getAnnualReportByRegion(CommonUtil.request2Json(request));
    }


}
