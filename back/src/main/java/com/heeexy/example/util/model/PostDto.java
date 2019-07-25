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
     */

    private String poster;

    private String posterAvatar;

    private String postId;

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

//    public String getPoster() {
//        return poster;
//    }
//
//    public void setPoster(String poster) {
//        this.poster = poster;
//    }
//
//    public String getPosterAvatar() {
//        return posterAvatar;
//    }
//
//    public void setPosterAvatar(String posterAvatar) {
//        this.posterAvatar = posterAvatar;
//    }
//
//    public int getPostId() {
//        return postId;
//    }
//
//    public void setPostId(int postId) {
//        this.postId = postId;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getMinPrice() {
//        return minPrice;
//    }
//
//    public void setMinPrice(String minPrice) {
//        this.minPrice = minPrice;
//    }
//
//    public String getMaxPrice() {
//        return maxPrice;
//    }
//
//    public void setMaxPrice(String maxPrice) {
//        this.maxPrice = maxPrice;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getPostLabels() {
//        return postLabels;
//    }
//
//    public void setPostLabels(String postLabels) {
//        this.postLabels = postLabels;
//    }
//
//    public int getBrowse() {
//        return browse;
//    }
//
//    public void setBrowse(int browse) {
//        this.browse = browse;
//    }
//
//    public int getLikes() {
//        return likes;
//    }
//
//    public void setLikes(int likes) {
//        this.likes = likes;
//    }
//
//    public int getCollections() {
//        return collections;
//    }
//
//    public String getPostGender() {
//        return postGender;
//    }
//
//    public void setPostGender(String postGender) {
//        this.postGender = postGender;
//    }
//
//    public void setCollections(int collections) {
//        this.collections = collections;
//    }
//
//    public int getFee() {
//        return fee;
//    }
//
//    public void setFee(int fee) {
//        this.fee = fee;
//    }
//
//    public Date getActiveTime() {
//        return activeTime;
//    }
//
//    public void setActiveTime(Date activeTime) {
//        this.activeTime = activeTime;
//    }
//
//    public List<JSONObject> getCommentList() {
//        return commentList;
//    }
//
//    public void setCommentList(List<JSONObject> commentList) {
//        this.commentList = commentList;
//    }
//
//    public List<JSONObject> getLikeList() {
//        return likeList;
//    }
//
//    public void setLikeList(List<JSONObject> likeList) {
//        this.likeList = likeList;
//    }
//
//    public List<JSONObject> getPostImgs() {
//        return postImgs;
//    }
//
//    public void setPostImgs(List<JSONObject> postImgs) {
//        this.postImgs = postImgs;
//    }
//
    private List<JSONObject> commentList;

    private List<JSONObject> likeList;

    private List<JSONObject> postImgs;

}