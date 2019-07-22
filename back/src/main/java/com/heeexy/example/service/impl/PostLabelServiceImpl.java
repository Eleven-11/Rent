package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostLabelDao;
import com.heeexy.example.service.PostLabelService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lingling
 * @description 帖子标签相关操作
 * @date 2019/7/3 0003
 */
@Service
public class PostLabelServiceImpl implements PostLabelService {
    @Autowired
    private PostLabelDao postLabelDao;

    @Override
    public JSONObject getPostLabelList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postLabelDao.countPostLabel(jsonObject);
        List<JSONObject> list = postLabelDao.getPostLabelList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject insertPostLabel(JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        map.put("contents",jsonObject.get("contents"));
        map.put("parentContent", jsonObject.get("parentContent"));
        postLabelDao.insertPostLabel(map);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }

    @Override
    public JSONObject updatePostLabel(JSONObject jsonObject) {
        //CommonUtil.fillPageParam(jsonObject);
        //System.out.println(jsonObject.toJSONString());
        postLabelDao.updatePostLabel(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }

    @Override
    public JSONObject deletePostLabel(JSONObject jsonObject) {
        //CommonUtil.fillPageParam(jsonObject);
        //System.out.println(jsonObject.toJSONString());
        postLabelDao.deletePostLabel(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }
}
