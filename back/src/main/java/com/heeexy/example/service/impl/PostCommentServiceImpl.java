package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.Const;
import com.heeexy.example.config.websocket.WebSocketServer;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.PostCommentDao;
import com.heeexy.example.dao.UserRestrictDao;
import com.heeexy.example.service.PostCommentService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    @Autowired
    private UserRestrictDao userRestrictDao;

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
    @Transactional(rollbackFor = Exception.class)
    public JSONObject insertComment(JSONObject jsonObject) {
        if (userRestrictDao.getResStatus(jsonObject) == 1) {
            return CommonUtil.errorJson(ErrorEnum.WX_884);
        } else {
            postCommentDao.insertComment(jsonObject);
            List<JSONObject> postCommentList = postCommentDao.getPostCommentList(jsonObject);
            jsonObject.put("postCommentList",postCommentList);
            //FIXME 发送评论推送给关联的用户,未测试
            send(jsonObject);
            return CommonUtil.successJson(jsonObject);
        }
    }

    /**
     * 逻辑删除评论
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateDelCommentById(JSONObject jsonObject) {
        if (postCommentDao.getCommentStatus(jsonObject) == 1) {
            return CommonUtil.successJson("评论已被删除，无需再次删除");
        }
        postCommentDao.updateDelCommentById(jsonObject);
        return CommonUtil.successJson("评论已删除，刷新后查看");
    }

    /**
     * @description 小程序：获取指定用户评论列表及被评论的帖子部分信息用于显示
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject getUserComment(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> userCommentList =postCommentDao.getUserCommentList(jsonObject);
        List<JSONObject> userCommentPostList=new ArrayList<>();
        for (int i=0;i<userCommentList.size();i++){
            JSONObject userCommentPost = postBaseDao.getWxUserPostInfo(userCommentList.get(i));
            if (userCommentPost ==null){
                continue;
            }
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

    /**
     * 通过评论实体发送评论通知
     * @param comment
     */
    private void send(JSONObject comment){
        try {
            String to = comment.getString("receiveId");
            //判断评论是否有指定接收人，如果没有去寻找帖子发布人
            if (StringUtils.isEmpty(to)){
                //通过postId获取帖子详情
                JSONObject post = postBaseDao.getWxUserPostInfo(comment);
                //如果帖子不为空
                if (post != null){
                    //设置消息接收人为发帖人
                    to  = post.getString("userId");
                }
            }
            //校验用户当前是否登录
            if (!StringUtils.isEmpty(to) && WebSocketServer.queryOnLine(to)){
                //通过commentId获取发送的消息实体
                JSONObject jo = postCommentDao.getMessageDataById(comment);
                WebSocketServer.sendMessage(jo,to, Const.SEND_COMMENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
