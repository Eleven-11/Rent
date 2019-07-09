package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PostBaseService {
    /**
     *@description:获取帖子列表
     */
    JSONObject getPostList(JSONObject jsonObject);
}
