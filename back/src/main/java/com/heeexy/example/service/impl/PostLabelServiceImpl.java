package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostLabelDao;
import com.heeexy.example.service.PostLabelService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lingling
 * @description 帖子标签相关操作
 * @date 2019/7/3 0003
 */
@Service
public class PostLabelServiceImpl implements PostLabelService {
    @Autowired
    private PostLabelDao postLabelDao;

    /**
     * 获取帖子标签列表
     * @param jsonObject
     * @return
     */
    @Override
    public List<JSONObject> getPostLabelList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postLabelDao.countPostLabel(jsonObject);
        List<JSONObject> list = postLabelDao.getPostLabelList(jsonObject);
        System.out.println(list);
        return list;
    }

    /**
     * 插入帖子标签
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject insertPostLabel(JSONObject jsonObject) {
        System.out.println(jsonObject);
        if (jsonObject.get("contents") != null && !StringUtils.isEmpty(jsonObject.get("contents"))) {
            jsonObject.put("contents", Arrays.asList(jsonObject.get("contents").toString().split(",")));
            postLabelDao.insertPostLabel(jsonObject);
            return CommonUtil.successJson("操作成功！请刷新后查看");
        }
        return CommonUtil.errorJson(ErrorEnum.E_90003);
    }

    /**
     * 更新帖子标签
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updatePostLabel(JSONObject jsonObject) {
        //CommonUtil.fillPageParam(jsonObject);
        //System.out.println(jsonObject.toJSONString());
        postLabelDao.updatePostLabel(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }

    /**
     * 删除帖子标签，逻辑删除
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject deletePostLabel(JSONObject jsonObject) {
        //CommonUtil.fillPageParam(jsonObject);
        //System.out.println(jsonObject.toJSONString());
        postLabelDao.deletePostLabel(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }
}
