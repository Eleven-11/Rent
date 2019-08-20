package com.heeexy.example.config.shiro.realm;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.shiro.token.LoginType;
import com.heeexy.example.dao.UserRestrictDao;
import com.heeexy.example.dao.VisitorDao;
import com.heeexy.example.dao.WxUserDao;
import com.heeexy.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author: chens
 * @description: 微信小程序处理realm
 * @date: 2019-07-26 14:09
 */
public class WxUserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(VueUserRealm.class);

    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private VisitorDao visitorDao;
    @Autowired
    private UserRestrictDao userRestrictDao;


    @Override
    public String getName(){
        return LoginType.WX_MINI_PEOJECT;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        logger.info("permission的值为:" + permission);
        logger.info("本用户权限为:" + permission.get("permissionList"));
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userId = (String) token.getPrincipal();
        JSONObject jo = new JSONObject();
        jo.put("userId",userId);
        JSONObject user = wxUserDao.queryUser(jo);
        //不是授权用户
        if (user == null) {
            user = visitorDao.queryUser(jo);
            //不是游客
            if (user == null){
                //没找到帐号
                throw new UnknownAccountException();
            }else {
                //权限级别 - 游客
                user.put("type",LoginType.WX_MINI_VISITOR);
            }
        }else {
            //判断是否受限
            if(userRestrictDao.getResStatus(jo) == 1){
                user.put("type", LoginType.WX_MINI_ASTRICT);
            }else {
                //权限级别 - 授权用户
                user.put("type", LoginType.WX_MINI_LEGAL);
            }
        }
        user.put("username",userId);
        user.put("password",userId);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getString("username"),
                user.getString("password"),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        //session中不需要保存密码
        user.remove("password");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        return authenticationInfo;
    }
}
