package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.PostDto;

import java.util.List;

public interface PostForMiniPrgService {
    /**
     * @description 获取帖子信息（基本信息及帖子图片、对应评论点赞列表）
     **/
    List<PostDto> getPostInfo(JSONObject jsonObject);
}
