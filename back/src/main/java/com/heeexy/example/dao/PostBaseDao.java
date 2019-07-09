package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.util.JAXBSource;
import java.util.List;

public interface PostBaseDao {
    /**
     *@description:获取帖子列表
     */
    List<JSONObject> getPostList(JSONObject jsonObject);

    int countPost(JSONObject jsonObject);
}
