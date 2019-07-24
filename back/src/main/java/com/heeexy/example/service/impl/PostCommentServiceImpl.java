package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.PostCommentDao;
import com.heeexy.example.service.PostCommentService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PostCommentServiceImpl
 * @Description 帖子评论相关操作
 * @Author Lingling00
 * @DATE 7/14/2019 17:43
 * @VERSION 1.0
 **/
@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Autowired
    private PostCommentDao postCommentDao;
    @Autowired
    private PostBaseDao postBaseDao;

    /**
     * 获取帖子评论列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostCommentList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postCommentDao.countComments(jsonObject);
        List<JSONObject> list = postCommentDao.getPostCommentList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 插入评论
     * @param jsonObject
     * @return
     */
    @Override
    public int insertComment(JSONObject jsonObject) {
        /*评论创建时间*/
        Date commentCreateTime = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(commentCreateTime));
        jsonObject.put("commentCreateTime",commentCreateTime);
        return postCommentDao.insertComment(jsonObject);
    }

    /**
     * 逻辑删除评论
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updateDelCommentById(JSONObject jsonObject) {
        if (postCommentDao.getCommentStatus(jsonObject) == 1) {
            return CommonUtil.successJson("评论已被删除，无需再次删除");
        }
        postCommentDao.updateDelCommentById(jsonObject);
        return CommonUtil.successJson("评论已删除，刷新后查看");
    }

    @Override
    public JSONObject getUserComment(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> userCommentList =postCommentDao.getUserCommentList(jsonObject);
        List<JSONObject> userCommentPostList=new ArrayList<>();
        for (int i=0;i<userCommentList.size();i++){
            System.out.println(userCommentList.get(i));
            JSONObject userCommentPost = postBaseDao.getUserPostInfo(userCommentList.get(i));

            //userCommentPost.put("postId",userCommentList.get(i).get("postId"));
            userCommentPost.put("commentCreateTime",userCommentList.get(i).get("commentCreateTime"));
            userCommentPost.put("startNickname",userCommentList.get(i).get("startNickname"));
            userCommentPost.put("startImg",userCommentList.get(i).get("startImg"));
            userCommentPost.put("receiveNickname",userCommentList.get(i).get("receiveNickname"));
            userCommentPost.put("receiveImg",userCommentList.get(i).get("receiveImg"));
            userCommentPost.put("commentContent",userCommentList.get(i).get("commentContent"));
            userCommentPostList.add(userCommentPost);
        }
        int count = userCommentList.size();
        return CommonUtil.successPage(jsonObject,userCommentPostList,count);
    }
}
