package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostTypeDao;
import com.heeexy.example.service.PostTypeService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingling
 * @description 可以帖子的八大类型（求租、合租、转租等），进行图标和类别名称的编辑和增删操作
 * @date 2019/7/2 0002
 */
@Service
public class PostTypeServiceImpl implements PostTypeService {
    @Autowired
    private PostTypeDao postTypeDao;

    /**
     *@description:获取帖子类别列表，已被逻辑删除的则不显示
     */
    @Override
    public JSONObject getPostTypeList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postTypeDao.countPostType(jsonObject);
        List<JSONObject> list = postTypeDao.getPostTypeList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     *@description:添加帖子类型信息
     */
    @Override
    public JSONObject insertPostType(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject.toJSONString());
        postTypeDao.insertPostType(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }


    /**
     *@description:根据id对帖子的类型进行修改
     */
    @Override
    public JSONObject updatePostTypeById(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject.toJSONString());
        postTypeDao.updatePostTypeById(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }

    /**
     *@description:根据id删除帖子类型信息
     */
    @Override
    public JSONObject deletePostTypeById(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject.toJSONString());
        postTypeDao.deletePostTypeById(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }


}
