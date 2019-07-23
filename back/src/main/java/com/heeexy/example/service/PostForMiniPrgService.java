package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.PostDto;

import java.util.List;

/**
 * @author: Lingling
 * @description: 小程序帖子基本信息业务逻辑
 * @date: 2019-07-23 17:33
 */
public interface PostForMiniPrgService {

    /**
     * 获取帖子信息（基本信息及帖子图片、对应评论点赞列表）
     * @param jsonObject
     * @return
     */
    List<PostDto> getPostInfo(JSONObject jsonObject);
}
