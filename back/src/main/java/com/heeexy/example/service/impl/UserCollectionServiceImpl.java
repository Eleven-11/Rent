package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PostBaseDao;
import com.heeexy.example.dao.UserCollectionDao;
import com.heeexy.example.service.UserCollectionService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private PostBaseDao postBaseDao;

    /**
     * 功能描述:根据用户ID查询用户收藏的帖子列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getUserCollList(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userCollectionDao.countCollection(jsonObject);
        List<JSONObject> list = userCollectionDao.getUserCollList(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 功能描述:对用户收藏的帖子进行排序
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject sortUserColl(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        System.out.println(jsonObject.toJSONString());
        userCollectionDao.sortUserColl(jsonObject);
        return CommonUtil.successJson("操作成功！请刷新后查看");
    }

    /**
     * 更新用户收藏信息（新增收藏、取消收藏、取消收藏后重新收藏）
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateUserCollection(JSONObject jsonObject) {
        //如果曾经收藏过
        //判断帖子是否存在，逻辑删除的也属于不存在
//        if (postBaseDao.getPostInfoByPostId(jsonObject) == null){
//            return CommonUtil.errorJson(ErrorEnum.WX_601);
//        }
        if(userCollectionDao.getIfCollected(jsonObject)!=null){
            userCollectionDao.updateDelCollect(jsonObject);
            JSONObject jo = new JSONObject();
            jo.put("collectStatus",userCollectionDao.getCollectStatus(jsonObject)==0?1:0);
            System.out.println(jo);
            return CommonUtil.successJson(jo);
        }
        else {
            userCollectionDao.insertUserCollection(jsonObject);
            JSONObject jo = new JSONObject();
            jo.put("collectStatus", userCollectionDao.getCollectStatus(jsonObject) == 0?1:0);
            System.out.println(jo);
            return CommonUtil.successJson(jo);
        }
    }
}
