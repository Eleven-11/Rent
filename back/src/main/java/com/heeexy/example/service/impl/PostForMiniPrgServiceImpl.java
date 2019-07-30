package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.WxPageException;
import com.heeexy.example.dao.*;
import com.heeexy.example.service.PostForMiniPrgService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 获取帖子信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostInfo(JSONObject jsonObject) throws WxPageException {
        //处理分页数据
        CommonUtil.WxPageParam(jsonObject);
        List<JSONObject> postBaseList = postBaseDao.getWxPostBaseList(jsonObject);
        //循环帖子集合，做相应的数据处理
        for (JSONObject jo : postBaseList){
            //获取帖子评论列表
            jo.put("postCommentList", postCommentDao.getPostCommentList(jo));
            //获取帖子图片集合
            jo.put("postImgList", postImgDao.getPostImgList(jo));
            //获取帖子点赞列表
            jo.put("postLikeList", userResonateDao.getPostLikeList(jo));
            //判断是否有用户id
            if (!StringUtils.isEmpty(jsonObject.get("userId"))){
                String userId = jsonObject.get("userId").toString();
                jo.put("userId",userId);
                //是否时自己发送的帖子
                if (userId.equals(jo.get("poster"))){
                    jo.put("myPost",1);
                }else{
                    jo.put("myPost",0);
                }
                //是否是自己收藏的帖子
                if (userCollectionDao.getIfCollect(jo) != null){
                    jo.put("myCollection",1);
                }else{
                    jo.put("myCollection",0);
                }
                //是否点赞过
                if (userResonateDao.getByUserIdPostId(jo) != null ){
                    jo.put("myLike",1);
                }else{
                    jo.put("myLike",0);
                }
            }
        }
       return CommonUtil.successPage(postBaseList);
    }
}
