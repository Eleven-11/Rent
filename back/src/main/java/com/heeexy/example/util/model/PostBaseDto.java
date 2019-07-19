package com.heeexy.example.util.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName PostBaseDto
 * @Description 帖子基本信息实体类
 * @Author Lingling00
 * @DATE 7/18/2019 17:08
 * @VERSION 1.0
 **/
@Data
public class PostBaseDto {

    private String poster;

    private String posterAvatar;

    private String postId;

    private Date createTime;

    private String content;

    private String minPrice;

    private String maxPrice;

    private String phone;

    private String postLabels;

    private String postGender;

    private int browse;

    private int devBrowse;

    private int likes;

    private int devLikes;

    private int collections;

    private int fee;

    private Date activeTime;
}
