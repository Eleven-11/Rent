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

import java.util.ArrayList;
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
    @Override
    public JSONObject insertNavigationTop(JSONObject jsonObject) {
        //判断置顶帖子条数是否超出5条
        if (navigationDao.getNavigationNum(jsonObject)==5) {
            return CommonUtil.errorJson(ErrorEnum.E_30001);
        }
        //判断要设置置顶的帖子是否已经在该模块置顶
        else if (navigationDao.getNavigationNum(jsonObject)<=5 &&navigationTopDao.getNavigationTopList(jsonObject)!=null){
            return CommonUtil.errorJson(ErrorEnum.WX_803);
        }
        //添加置顶帖子信息并返回主键topPostId
        else {
            int topPostId = navigationTopDao.insertNavigationTop(jsonObject);
            return CommonUtil.successJson(topPostId);
        }
    }
    @Override
    public List<JSONObject> getNavigationTopList(JSONObject jsonObject) {
        List<JSONObject> topJsonObject = navigationTopDao.getNavigationTopList(jsonObject);
        List<JSONObject> navigationTopList = new ArrayList<>();
        for (int i=0;i<topJsonObject.size();i++)
        {
            JSONObject navigationTop = (JSONObject) postBaseDao.getPostBaseList(topJsonObject.get(i));
            navigationTopList.add(navigationTop);
        }
        return navigationTopList;
    }
}
