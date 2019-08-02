package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 评论表数据库方法
 * @date: 2019-07-23 17:51
 */
public interface PostCommentDao {

    /**
     * 获取未读评论
     * userId   用户Id
     * flagTime 参考时间
     * @param jsonObject
     * @return
     * content          评论内容
     * postId           帖子Id
     * startId          评论者ID
     * startAvatarUrl   评论者头像
     * startNickName    评论者昵称
     * createTime       评论时间
     */
    List<JSONObject> getNewCommentListByUserIdFlagTime(JSONObject jsonObject);

    /**
     * 通过pcommentId获取评论通知推送的实体
     * @param jsonObject
     * @return
     */
    JSONObject getMessageDataById(JSONObject jsonObject);

    /**
     * 获取帖子评论列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostCommentList(JSONObject jsonObject);

    /**
     * 计算评论条数用于分页
     * @param jsonObject
     * @return
     */
    int countComments(JSONObject jsonObject);

    /**
     * 添加评论信息
     * @param jsonObject
     * @return
     */
    int insertComment(JSONObject jsonObject);

    /**
     * 逻辑删除评论
     * @param jsonObject
     */
    void updateDelCommentById(JSONObject jsonObject);

    /**
     * 获取评论状态信息（是否删除）
     * @param jsonObject
     * @return
     */
    int getCommentStatus(JSONObject jsonObject);
    /**
     * @description 获取指定用户评论列表
     * @param jsonObject 用户id-userId
     * @return 
     **/
    List<JSONObject> getUserCommentList(JSONObject jsonObject);
}
