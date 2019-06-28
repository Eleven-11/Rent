package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.UserCollectionDao;
import com.heeexy.example.service.UserCollectionService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingling
 * @description 用户收藏列表相关操作
 * @date 2019/6/27 0027
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService {
    @Autowired
    private UserCollectionDao userCollectionDao;
    /**
     *功能描述:根据用户ID查询用户收藏的帖子列表
     */
    @Override
    public JSONObject getUserCollList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userCollectionDao.countCollection(jsonObject);
        List<JSONObject> list = userCollectionDao.getUserCollList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
    /**
     *功能描述:对用户收藏的帖子进行排序
     */
    @Override
    public JSONObject updateUserColl(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject.toJSONString());
        userCollectionDao.updateUserColl(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }
}
