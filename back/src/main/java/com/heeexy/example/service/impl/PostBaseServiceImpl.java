package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.PostImgDao;
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


    /**
     * 获取帖子基本信息列表List
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostBaseList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postBaseDao.countPost(jsonObject);
        List<JSONObject> list = postBaseDao.getPostBaseList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 更改帖子点赞量、浏览量、帖子上架下架
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

        /*FIXME
            postId还是int自增长，该插入字段会自动返回数据表id并且变量名为postId
            如需修改postId为String自定义ID，请修改所有带有postId的表字段，并修改
            下面的插入方法对应的xml实现
        */
        if (postBaseDao.getReleaseTime(jsonObject) <= 5) {
            postBaseDao.insertPostBase(jsonObject);
            //判断是否有上传图片集合
            if (jsonObject.get("postImgList") != null && !StringUtils.isEmpty(jsonObject.get("postImgList"))) {
            /*FIXME
                以下图片集合的获取以postman测试工具测试成功
                并不保证正式环境下小程序的图片集合处理
                如出现异常，请根据postImgList对应的字段格式做相应处理
            */
                jsonObject.put("postImgList", Arrays.asList(jsonObject.get("postImgList").toString().split(",")));
                postImgDao.insertPostImgList(jsonObject);
            }
            return CommonUtil.successJson("发布成功！");
        }
        return CommonUtil.errorJson(ErrorEnum.E_30001);
    }
}
