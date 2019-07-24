package com.heeexy.example.dao;

import net.sf.json.JSONObject;

import java.util.List;

public interface UserBrowseDao {
    /**
     * @description 用户浏览过的帖子id列表
     * @param
     * @return
     **/
    List<JSONObject> getUserBrowse(JSONObject jsonObject);
}
