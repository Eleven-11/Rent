package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PostBaseController
 * @Description 帖子基本信息相关操作
 * @Author Lingling00
 * @DATE 7/16/2019 16:55
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/postBase")
public class PostBaseController {
    @Autowired
    private PostBaseService postBaseService;

    /**
     * @description 获取帖子列表（只包括基本信息）
     * @param request （帖子id-postId，发帖人昵称-nickname（用于获取指定用户发布的帖子列表，非必须），关键词-keyword（用于进行模糊查询，包括地址及帖子内容））
     * @return 帖子列表
     *         发帖人-poster,
     *         发帖人头像-posterAvatar,
     *         发帖人性别-posterGender,
     *         帖子类别-typeName，
     *         帖子Id-postId,
     *         帖子创建时间-createTime,
     *         地址-address,
     *         帖子内容content,
     *         最低价-minPrice,
     *         最高价- maxPrice,
     *         联系方式-phone,
     *         真实浏览量-browse,
     *         偏移浏览量-devBrowse,
     *         评论数量-comments,
     *         点赞真实量-likes,
     *         点赞偏移量-devLike，
     *         帖子收藏量-collection，
     *         中介费-fee,
     *         活跃时间-activeTime
     **/
    @GetMapping("/getPostBaseList")
    public JSONObject getPostBaseList(HttpServletRequest request) {
        return postBaseService.getPostBaseList(CommonUtil.request2Json(request));
    }

    /**
     * 更新帖子
     * @param request
     * @return
     */
    @PostMapping("/updatePostBase")
    public JSONObject updatePostBase(HttpServletRequest request){
        return postBaseService.updatePostBase(CommonUtil.request2Json(request));
    }

}
