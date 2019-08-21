package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.NavigationDao;
import com.heeexy.example.dao.NavigationTopDao;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.service.NavigationTopService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName NavigationTopServiceImpl
 * @Description 导航栏置顶帖子相关操作
 * @Author Lingling00
 * @DATE 7/29/2019 14:04
 * @VERSION 1.0
 **/
@Service
public class NavigationTopServiceImpl implements NavigationTopService {
    @Autowired
    private NavigationDao navigationDao;
    @Autowired
    private NavigationTopDao navigationTopDao;
    @Autowired
    private PostBaseDao postBaseDao;
    /**
     * @description 添加置顶帖子
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject insertNavigationTop(JSONObject jsonObject) {
        //将传进来的导航栏id字符串分割成数组
        List<String> navigationIds = Arrays.asList(jsonObject.get("checkedNavs").toString().split(","));
        //循环插入置顶信息
        for (int i=0;i<navigationIds.size();i++) {
            jsonObject.put("navigationId", navigationIds.get(i));
            //判断置顶帖子条数是否超出5条
            System.out.println(jsonObject);
            if (navigationDao.getNavigationNum(jsonObject) == 5) {
                return CommonUtil.errorJson(ErrorEnum.E_30001);
            }
            //判断要设置置顶的帖子是否已经在该模块置顶
            else if (navigationDao.getNavigationNum(jsonObject) <= 5 && !navigationTopDao.getNavigationTopList(jsonObject).isEmpty()) {
                return CommonUtil.errorJson("该帖子已置顶于"+navigationDao.getNavigationTitle(jsonObject).get(0).get("navigationTitle"));
            }
            else {
                navigationTopDao.insertNavigationTop(jsonObject);
                navigationDao.updateTopNum(jsonObject);
            }
        }
        return CommonUtil.successJson("设置置顶成功");
    }
    /**
     * @description 获取模块置顶的帖子列表
     * @param jsonObject
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    @Override
    public List<JSONObject> getNavigationTopList(JSONObject jsonObject) {
        List<JSONObject> topJsonObject = navigationTopDao.getNavigationTopList(jsonObject);
        List<JSONObject> navigationTopList = new ArrayList<>();
        for (int i=0;i<topJsonObject.size();i++)
        {
            JSONObject navigationTop = postBaseDao.getTopPostBase(topJsonObject.get(i));
            navigationTop.put("navigationId",topJsonObject.get(i).get("navigationId"));
            navigationTop.put("navigationTitle",topJsonObject.get(i).get("navigationTitle"));
            navigationTop.put("topPostId",topJsonObject.get(i).get("topPostId"));
            navigationTop.put("sortTime",topJsonObject.get(i).get("sortTime"));
            navigationTopList.add(navigationTop);
        }
        return navigationTopList;
    }

    /**
     * @description 更新置顶帖子
     * @param jsonObject
     * @return void
     **/
    @Override
    public JSONObject updateNavigationTop(JSONObject jsonObject) {
        navigationTopDao.updateNavigationTop(jsonObject);
        return CommonUtil.successJson("修改成功，刷新后查看！");
    }

    /**
     * @description 获取置顶模块列表
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject getNavigationTitle(JSONObject jsonObject) {
        return CommonUtil.successJson(navigationDao.getNavigationTitle(jsonObject));
    }

    /**
     * @description 对置顶帖子进行排序
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    public JSONObject sortNavigationTop(JSONObject jsonObject) {
        navigationTopDao.sortNavigationTop(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * @description 删除置顶帖子信息
     * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateDelNavigationTop(JSONObject jsonObject) {
        navigationTopDao.updateDelNavigationTop(jsonObject);
        return CommonUtil.successJson("已删除置顶信息");
    }
}
