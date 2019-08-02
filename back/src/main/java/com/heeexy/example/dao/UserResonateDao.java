package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: Lingling
 * @description: 用户点赞表数据库方法
 * @date: 2019-07-23 17:56
 */
public interface UserResonateDao {

    /**
     * 获取未查看的点赞消息
     * userId     用户ID
     * flagTime   参考时间
     * @param jsonObject
     * @return
     * postId           帖子Id
     * resId            点赞人ID
     * resAvatarUrl     点赞人头像
     * resNickName      点赞人昵称
     * createTime       点赞时间
     */
    //FIXME 未测试
    List<JSONObject> getNewResonateListByUserIdFlagTime(JSONObject jsonObject);

    /**
     * 通过likeId获取发送的点赞消息数据
     * @param jsonObject
     * @return
     */
    JSONObject getMessageDataById(JSONObject jsonObject);

    /**
     * @description 判断用户是否点赞过，用于更新点赞状态（即包括取消点赞状态的记录）
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    JSONObject getIfLiked(JSONObject jsonObject);

    /**
     * 获取点赞列表条数用于前端分页
     *
     * @param jsonObject
     * @return
     */
    int countPostList(JSONObject jsonObject);

    /**
     * 获取点赞列表(包括点赞人昵称及其头像)
     *
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPostLikeList(JSONObject jsonObject);

    /**
     * 添加点赞信息并返回主键
     *
     * @param jsonObject
     */
    void insertPostLike(JSONObject jsonObject);

    /**
     * 取消点赞
     *
     * @param jsonObject
     * @return
     */
    int updateDelPostLike(JSONObject jsonObject);
    /**
     * 获取用户点赞过的帖子id
     * @param jsonObject
     * @return
     **/
    List<JSONObject> getUserLikeList(JSONObject jsonObject);

    /**
     * 判断用户是否点赞
     * is_del = 0
     * @param jsonObject
     */
    JSONObject getByUserIdPostId(JSONObject jsonObject);
    /**
     * @description 判断用户当前点赞状态用于返回给前端更改页面状态
     * @param
     * @return
     **/
    int getLikeStatus(JSONObject jsonObject);

}
