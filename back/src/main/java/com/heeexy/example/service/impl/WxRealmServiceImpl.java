package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.shiro.token.LoginType;
import com.heeexy.example.config.shiro.token.ShiroConfigToken;
import com.heeexy.example.dao.WxPermissionDao;
import com.heeexy.example.service.WxRealmService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mac
 * @description: 微信用户授权业务层
 * @date: 2019-08-18 15:43
 */
@Service
public class WxRealmServiceImpl implements WxRealmService {

    @Autowired
    private WxPermissionDao wxPermissionDao;

    /**
     * 登陆认证
     * @param userId
     */
    @Override
    public String wxLogin(String userId){
        Subject currentUser = SecurityUtils.getSubject();
        ShiroConfigToken token = new ShiroConfigToken(userId, userId, LoginType.WX_MINI_PEOJECT);
        currentUser.login(token);
        return SecurityUtils.getSubject().getSession().getId().toString();
    }

    /**
     * 授权
     * @return
     */
    @Override
    public JSONObject getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        //声明权限标签集合
        List<String> permissionList = new ArrayList<>();
        //获取用户类型
        String type = userInfo.getString("type");
        if (!StringUtils.isEmpty(type)){
            //判断用户类型
            if (LoginType.WX_MINI_LEGAL.equals(type)){
                //获取授权用户权限
                permissionList = wxPermissionDao.listByType(null);
            }else if (LoginType.WX_MINI_ASTRICT.equals(type)){
                //获取限制用户权限
                permissionList = wxPermissionDao.listByType(null);
            }
        }
        JSONObject userPermission = new JSONObject();
        //设置权限列表
        userPermission.put("permissionList",permissionList);
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        JSONObject info = new JSONObject();
        info.put("userPermission", userPermission);
        return CommonUtil.successJson(info);
    }
}
