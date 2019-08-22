package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.WxPageException;
import com.heeexy.example.dao.*;
import com.heeexy.example.service.PostForMiniPrgService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName PostForMiniPrgServiceImpl
 * @Description 小程序帖子信息相关操作
 * @Author Lingling00
 * @DATE 7/18/2019 16:03
 * @VERSION 1.0
 **/
@Service
public class PostForMiniPrgServiceImpl implements PostForMiniPrgService {

    @Autowired
    private PostBaseDao postBaseDao;

    @Autowired
    private PostImgDao postImgDao;

    @Autowired
    private UserResonateDao userResonateDao;

    @Autowired
    private PostCommentDao postCommentDao;

    @Autowired
    private UserCollectionDao userCollectionDao;

    @Autowired
    private NavigationTopDao navigationTopDao;

    /**
     * 获取帖子信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostInfo(JSONObject jsonObject) throws WxPageException {
        //处理分页数据
        CommonUtil.WxPageParam(jsonObject);
        //对查询的labels条件进行处理
        if (jsonObject.get("labels") != null && !StringUtils.isEmpty(jsonObject.get("labels"))) {
            List<String> labels = Arrays.asList(jsonObject.get("labels").toString().split(","));
            jsonObject.put("labels", labels);
            jsonObject.put("labelNum",labels.size());
        }
        List<JSONObject> postBaseList = new ArrayList<>();
        //加载列表时
        if (jsonObject.get("postId") == null) {
            //当加载的帖子列表为模块第一页时加载模块置顶的帖子
            if (jsonObject.getInteger("pageNum")==1 && jsonObject.get("navigationId")!=null) {
                //模块置顶帖子id列表
                List<JSONObject> topJsonObject = navigationTopDao.getWxNavigationTopList(jsonObject);
                //模块置顶帖子列表
                List<JSONObject> topPostBase = new ArrayList<>();
                if (!topJsonObject.isEmpty()) {
                    for (int i = 0; i < topJsonObject.size(); i++) {
                        JSONObject navigationTop = postBaseDao.getTopPostBase(topJsonObject.get(i));
                        topPostBase.add(navigationTop);
                    }
                }
                for (int i = 0; i < topPostBase.size(); i++) {
                    if (topPostBase.get(i)!=null) {
                        postBaseList.add(topPostBase.get(i));
                    }
                }
            }
            //除置顶帖子以外的全部帖子列表
            List<JSONObject> bottomPost = postBaseDao.getWxPostBaseList(jsonObject);
            for (int i = 0; i < bottomPost.size(); i++) {
                postBaseList.add(bottomPost.get(i));
            }
            //循环帖子集合，做相应的数据处理
            for (JSONObject jo : postBaseList) {
                //获取帖子评论列表
                jo.put("postCommentList", postCommentDao.getPostCommentList(jo));
                //获取帖子图片集合
                jo.put("postImgList", postImgDao.getPostImgList(jo));
                //获取帖子点赞列表
                List<JSONObject> postLikeList = userResonateDao.getPostLikeList(jo);
                //没有头像的用户设置为默认头像
                for (int i = 0; i < postLikeList.size(); i++) {
                    if (postLikeList.get(i).get("likeImg") == null) {
                        postLikeList.get(i).put("likeImg", "http://192.168.1.7:8080/image/static/default.png");
                    }
                }
                jo.put("postLikeList", postLikeList);
                //判断是否有用户id
                if (!StringUtils.isEmpty(jsonObject.get("userId"))) {
                    String userId = jsonObject.get("userId").toString();
                    jo.put("userId", userId);
                    //是否时自己发送的帖子
                    if (userId.equals(jo.get("poster"))) {
                        jo.put("myPost", 1);
                    } else {
                        jo.put("myPost", 0);
                    }
                    //是否是自己收藏的帖子
                    if (userCollectionDao.getIfCollect(jo) != null) {
                        jo.put("myCollection", 1);
                    } else {
                        jo.put("myCollection", 0);
                    }
                    //是否点赞过
                    if (userResonateDao.getByUserIdPostId(jo) != null) {
                        jo.put("myLike", 1);
                    } else {
                        jo.put("myLike", 0);
                    }
                }
            }
            return CommonUtil.successPage(postBaseList);
        }
    else{
        postBaseList = postBaseDao.getWxPostBaseList(jsonObject);
            for (JSONObject jo : postBaseList) {
                //获取帖子评论列表
                jo.put("postCommentList", postCommentDao.getPostCommentList(jo));
                //获取帖子图片集合
                jo.put("postImgList", postImgDao.getPostImgList(jo));
                //获取帖子点赞列表
                List<JSONObject> postLikeList = userResonateDao.getPostLikeList(jo);
                //没有头像的用户设置为默认头像
                for (int i = 0; i < postLikeList.size(); i++) {
                    if (postLikeList.get(i).get("likeImg") == null) {
                        postLikeList.get(i).put("likeImg", "http://192.168.1.7:8080/image/static/default.png");
                    }
                }
                jo.put("postLikeList", postLikeList);
                //判断是否有用户id
                if (!StringUtils.isEmpty(jsonObject.get("userId"))) {
                    String userId = jsonObject.get("userId").toString();
                    jo.put("userId", userId);
                    //是否时自己发送的帖子
                    if (userId.equals(jo.get("poster"))) {
                        jo.put("myPost", 1);
                    } else {
                        jo.put("myPost", 0);
                    }
                    //是否是自己收藏的帖子
                    if (userCollectionDao.getIfCollect(jo) != null) {
                        jo.put("myCollection", 1);
                    } else {
                        jo.put("myCollection", 0);
                    }
                    //是否点赞过
                    if (userResonateDao.getByUserIdPostId(jo) != null) {
                        jo.put("myLike", 1);
                    } else {
                        jo.put("myLike", 0);
                    }
                }
            }
            return CommonUtil.successPage(postBaseList);
        }
    }
}
