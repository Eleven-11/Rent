package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.UserRestrictDao;
import com.heeexy.example.service.UserRestrictService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lingling
 * @description 用户权限功能实现
 * @date 2019/6/28 0028
 */
@Service
public class UserRestrictServiceImpl implements UserRestrictService {

    @Autowired
    private UserRestrictDao userRestrictDao;

    /**
     * 更新小程序用户权限
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject insertWxUserRes(JSONObject jsonObject) {
        //用户曾经被禁言过
        if (userRestrictDao.getIfRestricted(jsonObject) != null) {
            //用户当下正处于禁言状态
            if (userRestrictDao.getResStatus(jsonObject) == 1) {
                return CommonUtil.errorJson(ErrorEnum.WX_886);
            }
            //用户当下未被禁言
            else {
                userRestrictDao.updateWxUserRes(jsonObject);
                return CommonUtil.successJson("用户已被禁言");
            }
        }
        //用户从未被禁言
        else {
            userRestrictDao.insertWxUserRes(jsonObject);
            return CommonUtil.successJson("添加禁言成功");
        }
    }

    @Override
    public JSONObject updateDelWxUserRes(JSONObject jsonObject) {
        if (userRestrictDao.getResStatus(jsonObject)==0){
            return CommonUtil.errorJson(ErrorEnum.WX_885);
        }
        else {
            userRestrictDao.updateDelWxUserRes(jsonObject);
            return CommonUtil.successJson("设置成功");
        }
    }

}
