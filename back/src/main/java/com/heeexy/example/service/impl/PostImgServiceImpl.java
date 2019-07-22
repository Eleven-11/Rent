package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostImgDao;
import com.heeexy.example.service.PostImgService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName PostImgServiceImpl
 * @Description 帖子图片相关操作
 * @Author Lingling00
 * @DATE 7/18/2019 15:24
 * @VERSION 1.0
 **/
@Service
public class PostImgServiceImpl implements PostImgService {
    @Autowired
    private PostImgDao postImgDao;

    @Override
    public List<JSONObject> getPostImgList(JSONObject jsonObject) {
        return postImgDao.getPostImgList(jsonObject);
    }

    @Override
    public JSONObject insertPostImgList(JSONObject jsonObject) {
        Date createTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("postId",jsonObject.get("postId"));
        map.put("postImgList", jsonObject.get("postImgList"));
        map.put("createTime",formatter.format(createTime));
        postImgDao.insertPostImgList(map);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }
}
