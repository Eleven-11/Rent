package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostCommentDao;
import com.heeexy.example.service.PostCommentService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /*获取帖子评论列表*/
    @Override
    public JSONObject getPostCommentList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postCommentDao.countComments(jsonObject);
        List<JSONObject> list = postCommentDao.getCommentList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /*插入评论*/
    @Override
    public int insertComment(JSONObject jsonObject) {
        return postCommentDao.insertComment(jsonObject);
    }

    /*逻辑删除评论*/
    @Override
    public JSONObject updateDelCommentById(JSONObject jsonObject) {
        if (postCommentDao.getCommentStatus(jsonObject) == 1) {
            return CommonUtil.successJson("评论已被删除，无需再次删除");
        }
        postCommentDao.updateDelCommentById(jsonObject);
        return CommonUtil.successJson("评论已删除，刷新后查看");
    }
}
