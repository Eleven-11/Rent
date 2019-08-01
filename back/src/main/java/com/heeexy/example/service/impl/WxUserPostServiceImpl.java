package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.exception.WxPageException;
import com.heeexy.example.dao.PostCommentDao;
import com.heeexy.example.dao.PostImgDao;
import com.heeexy.example.dao.UserResonateDao;
import com.heeexy.example.dao.WxUserPostDao;
import com.heeexy.example.service.WxUserPostService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chens
 * @description: 微信用户帖子操作逻辑实现
 * @date: 2019-07-29 11:55
 */
@Service
public class WxUserPostServiceImpl implements WxUserPostService {

    @Autowired
    private WxUserPostDao wxUserPostDao;

    @Autowired
    private PostImgDao postImgDao;

    @Autowired
    private PostCommentDao postCommentDao;

    @Autowired
    private UserResonateDao userResonateDao;

    /**
     * 用户发帖列表
     * 分页加载
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getWxUserPostList(JSONObject jsonObject) throws WxPageException {
        //处理分页信息
        jsonObject = CommonUtil.WxPageParam(jsonObject);
        //分页加载获取对应的帖子列表
        List<JSONObject> upList = wxUserPostDao.getWxUserPostList(jsonObject);
        //遍历处理帖子集合
        for (JSONObject jo : upList){
//            jo.put("likes")
//                    jo.put("browse",)
            //获取帖子图片集合
            jo.put("postImgList", postImgDao.getPostImgList(jo));
            //获取帖子评论集合
            jo.put("postCommentList", postCommentDao.getPostCommentList(jo));
            //获取帖子点赞列表
            jo.put("postLikeList", userResonateDao.getPostLikeList(jo));
        }
        return CommonUtil.successPage(upList);
    }

    /**
     * @description 用户设置帖子上下架
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject updateOnShelf(JSONObject jsonObject) {
        if (wxUserPostDao.queryPostByUserIdPostId(jsonObject)!=null){
            wxUserPostDao.updateOnShelf(jsonObject);
            return CommonUtil.successJson("已下架");
        }
        else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }
}
