package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostTypeDao;
import com.heeexy.example.service.PostTypeService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
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
     * 获取帖子类别列表，已被逻辑删除的则不显示
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPostTypeList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postTypeDao.countPostType(jsonObject);
        List<JSONObject> list = postTypeDao.getPostTypeList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject getBackPostTypeList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postTypeDao.countBackPostType(jsonObject);
        List<JSONObject> list = postTypeDao.getBackPostTypeList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 添加帖子类型信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject insertPostType(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = postTypeDao.countPostType(jsonObject);
        if(count==8) {
            return CommonUtil.errorJson(ErrorEnum.E_30001);
        }
        else {
            postTypeDao.insertPostType(jsonObject);
            return CommonUtil.successJson(jsonObject.get("postTypeId"));
        }
    }

    @Override
    public JSONObject sortPostType(JSONObject jsonObject) {
       postTypeDao.sortPostType(jsonObject);
       return CommonUtil.successJson();
    }


    /**
     * 根据id对帖子的类型进行修改
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updatePostTypeById(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        postTypeDao.updatePostTypeById(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 根据id删除帖子类型信息
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updateDelPostTypeById(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        postTypeDao.updateDelPostTypeById(jsonObject);
        return CommonUtil.successJson();
    }


}
