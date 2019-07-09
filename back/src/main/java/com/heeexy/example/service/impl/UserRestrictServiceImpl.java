package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.UserRestrictDao;
import com.heeexy.example.service.UserRestrictService;
import com.heeexy.example.util.CommonUtil;
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

    @Override
    public JSONObject updateWxUserRes(JSONObject jsonObject) {
        userRestrictDao.updateWxUserRes(jsonObject);
        return CommonUtil.successJson("操作成功！");
    }
}
