package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.common.Const;
import com.heeexy.example.config.websocket.WebSocketServer;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.UserResonateDao;
import com.heeexy.example.dao.UserRestrictDao;
import com.heeexy.example.service.UserResonateService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserResonateServiceImpl
 * @Description 用户点赞相关操作
 * @Author Lingling00
 * @DATE 7/15/2019 13:56
 * @VERSION 1.0
 **/
@Service
public class UserResonateServiceImpl implements UserResonateService {

    @Autowired
    private UserResonateDao userResonateDao;
    @Autowired
    private PostBaseDao postBaseDao;
    @Autowired
    private UserRestrictDao userRestrictDao;

    /**
     * 获取帖子点赞列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostLikeList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        if (jsonObject.get("limit")!=null &&jsonObject.get("limit")!=""){
            List<JSONObject> list = userResonateDao.getPostLikeList(jsonObject);
            for (int i=0;i<list.size();i++){
                if (list.get(i).get("likeImg")==null){
                    list.get(i).put("likeImg","http://192.168.1.8:8080/image/static/default.png");
                }
            }

            return CommonUtil.successPage(jsonObject, list, (Integer) jsonObject.get("limit"));
        }
        else {
            int count = userResonateDao.countPostList(jsonObject);
            List<JSONObject> list = userResonateDao.getPostLikeList(jsonObject);
            for (int i=0;i<list.size();i++){
                if (list.get(i).get("likeImg")==null){
                    list.get(i).put("likeImg","http://192.168.1.8:8080/image/static/default.png");
                }
            }
            return CommonUtil.successPage(jsonObject, list, count);
        }
    }

    /**
     * 添加点赞信息、取消点赞、取消后重新点赞
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updatePostLike(JSONObject jsonObject) {
        //用户处于禁言状态无法点赞
        if (userRestrictDao.getResStatus(jsonObject) == 1) {
            return CommonUtil.errorJson(ErrorEnum.WX_884);
        } else {
            //判断是否已存在点赞记录
            if (userResonateDao.getIfLiked(jsonObject) != null) {
                userResonateDao.updateDelPostLike(jsonObject);
                JSONObject jo = new JSONObject();
                jo.put("likeStatus",userResonateDao.getLikeStatus(jsonObject)==0?1:0);
                return CommonUtil.successJson(jo);
            } else {
                userResonateDao.insertPostLike(jsonObject);
                //FIXME 如果是第一次点赞，发送推送给用户,未测试
                send(jsonObject);
                JSONObject jo = new JSONObject();
                jo.put("likeStatus",userResonateDao.getLikeStatus(jsonObject)==0?1:0);
                return CommonUtil.successJson(jo);
            }
        }
    }
    /**
     * @description 获取用户点赞列表
     * @param jsonObject
     * @return
     **/
    @Override
    public JSONObject getUserLikePostList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        //用户点赞的帖子id列表
        List<JSONObject> userLikePostIds =userResonateDao.getUserLikeList(jsonObject);

        List<JSONObject> userLikePostList=new ArrayList<>();

        for (int i=0;i<userLikePostIds.size();i++){
            //根据帖子id获取具体信息
            JSONObject userLikePost = postBaseDao.getWxUserPostInfo(userLikePostIds.get(i));
            //当点赞的帖子已删除等情况导致获取到空值，则不返回该条记录
            if (userLikePost!=null) {
                userLikePostList.add(userLikePost);
            }
        }
        int count = userLikePostList.size();
        return CommonUtil.successPage(jsonObject,userLikePostList,count);
    }


    /**
     * 发送点赞消息给发帖人
     * 仅第一次点赞有效，取消后在点赞无效
     * @param resonate
     */
    private void send(JSONObject resonate){
        try {
            //获取帖子实体
            JSONObject post = postBaseDao.getWxUserPostInfo(resonate);
            //如果有帖子实体
            if (post != null){
                String to = post.getString("userId");
                //判断接受者是否在线
                if (!StringUtils.isEmpty(to) && WebSocketServer.queryOnLine(to)){
                    //通过
                    JSONObject jo = userResonateDao.getMessageDataById(resonate);

                        WebSocketServer.sendMessage(jo,to, Const.SEND_RESONATE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
