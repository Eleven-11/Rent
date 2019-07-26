package com.heeexy.example.config.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author: chens
 * @description: 重构shiroToken对象
 * @date: 2019-07-26 13:50
 */
@Data
public class ShiroConfigToken extends UsernamePasswordToken {

    /**
     * 登陆类型
     * 微信小程序： wx
     * 后端管理界面： vue
     */
    private String loginType;

    public ShiroConfigToken(){

    }

    /**
     * 重写shiroToken的构造方法
     * @param username 用户名
     * @param password 密码
     * @param loginType 登陆类型
     */
    public ShiroConfigToken(String username, String password, String loginType){
        super(username,password);
        this.loginType = loginType;
    }
}
