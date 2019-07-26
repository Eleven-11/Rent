package com.heeexy.example.util.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PostDto
 * @Description 帖子信息
 * @Author Lingling00
 * @DATE 7/18/2019 11:17
 * @VERSION 1.0
 **/
@Data
public class PostDto {
    /**
     * 发帖人-poster,
     * 发帖人头像-posterAvatar,
     * 发帖人性别-posterGender，
     * 是否关注发帖人-isFollow,
     * 帖子Id-postId,
     * 帖子创建时间-createTime,
     * 帖子类别名称-typeName,
     * 地址-address,
     * 帖子内容content,
     * 最低价-minPrice,
     * 最高价- maxPrice,
     * 联系方式-phone,
     * 帖子标签集合-postLabels，
     * 浏览量-browse，
     * 评论数量-comments,
     * 评论列表-commentList，
     * 图片集合-postImgs，
     * 点赞列表-likeList，
     * 点赞量-likes，
     * 帖子收藏量-collections，
     * 中介费-fee,
     * 活跃时间-activeTime
     * 评论集合 commentList
     * 点赞集合 likeList
     * 图片集合 postImgs
     */

    private String poster;

    private String posterAvatar;

    private int postId;

    private Date createTime;

    private String content;

    private String typeName;

    private String minPrice;

    private String maxPrice;

    private String phone;

    private int comments;

    private String postLabels;

    private String postGender;

    private int browse;

    private int likes;

    private int collections;

    private int fee;

    private Date activeTime;

    private List<JSONObject> commentList;

    private List<JSONObject> likeList;

    private List<JSONObject> postImgs;

    /**
     * 是否时我发的帖子
     */
    private Integer myPost = 0;
    /**
     * 是否点过赞
     */
    private Integer myLike = 0;
    /**
     * 是否收藏过
     */
    private Integer myCollection = 0;
}