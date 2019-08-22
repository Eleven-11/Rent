package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.*;
import com.heeexy.example.service.PostBaseService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName PostBaseServiceImpl
 * @Description 帖子基本表相关操作
 * @Author Lingling00
 * @DATE 7/14/2019 14:39
 * @VERSION 1.0
 **/
@Service
@Transactional
public class PostBaseServiceImpl implements PostBaseService {

    @Autowired
    private PostBaseDao postBaseDao;
    @Autowired
    private PostImgDao postImgDao;
    @Autowired
    private PostLabelDao postLabelDao;
    @Autowired
    private UserRestrictDao userRestrictDao;
    @Autowired
    private NavigationTopDao navigationTopDao;

    /**
     * 获取帖子基本信息列表List
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostBaseList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject);
        int count = postBaseDao.countPost(jsonObject);
        List<JSONObject> list = postBaseDao.getPostBaseList(jsonObject);
        System.out.println("获取了帖子列表");
        System.out.println(list);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 更改帖子点赞量、浏览量
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updatePostBase(JSONObject jsonObject) {
        if((jsonObject.get("devLike")==null||jsonObject.get("devLike")=="")&&(jsonObject.get("devBrowse")==null||jsonObject.get("devBrowse")=="")){
            return CommonUtil.errorJson(ErrorEnum.E_90003);
        }
        else
        {
            postBaseDao.updatePostBase(jsonObject);
            return CommonUtil.successJson("更新成功，刷新后查看");
        }
    }

    /**
     * 发布帖子
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject insertPostBase(JSONObject jsonObject) {
//        String postId = UUIDUtils.getUUID();
//        jsonObject.put("postId", postId);
        //用户处于禁言状态时
        if (userRestrictDao.getResStatus(jsonObject) == 1) {
            return CommonUtil.errorJson(ErrorEnum.WX_884);
        } else {
            if (postBaseDao.getReleaseTime(jsonObject) < 5) {
                if (!StringUtils.isEmpty(jsonObject.getString("maxPrice"))) {
                    if (jsonObject.getInteger("minPrice") > jsonObject.getInteger("maxPrice")) {
                        return CommonUtil.errorJson(ErrorEnum.WX_807);
                    }
                }
                postBaseDao.insertPostBase(jsonObject);
                if (jsonObject.get("labels") != null && !StringUtils.isEmpty(jsonObject.get("labels"))) {
                    jsonObject.put("labels", Arrays.asList(jsonObject.get("labels").toString().split(",")));
                    postLabelDao.insertLabel(jsonObject);
                }
                //判断是否有上传图片集合
                if (jsonObject.get("postImgList") != null && !StringUtils.isEmpty(jsonObject.get("postImgList"))) {
                    jsonObject.put("postImgList", Arrays.asList(jsonObject.get("postImgList").toString().split(",")));
                    postImgDao.insertPostImgList(jsonObject);
                }
                return CommonUtil.successJson(jsonObject.get("postId"));
            }
            return CommonUtil.errorJson(ErrorEnum.E_30001);
        }
    }

    /**
     * 后台：设置帖子上下架
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject updateOnShelf(JSONObject jsonObject) {
        postBaseDao.updateOnShelf(jsonObject);
        if (postBaseDao.getOnShelfStatus(jsonObject)==1 && navigationTopDao.getNavigationTopList(jsonObject)!=null){
            jsonObject.put("topPostId",navigationTopDao.getNavigationTopList(jsonObject).get(0).get("topPostId"));
            jsonObject.put("navigationId",navigationTopDao.getNavigationTopList(jsonObject).get(0).get("navigationId"));
            navigationTopDao.updateDelNavigationTop(jsonObject);
        }
        return CommonUtil.successJson();
    }

    /**
     * 后台： 逻辑删除帖子
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateDelPost(JSONObject jsonObject) {
        postBaseDao.updateDelPost(jsonObject);
        return CommonUtil.successJson("删除成功");
    }

    /**
     * @description 恢复逻辑删除的帖子
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject recoverPost(JSONObject jsonObject) {
        postBaseDao.updateDeletedPost(jsonObject);
        return CommonUtil.successJson("恢复帖子成功，刷新后查看");
    }

}
