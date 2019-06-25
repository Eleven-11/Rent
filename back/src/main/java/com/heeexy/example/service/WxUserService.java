package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;

public interface WxUserService {
    /*微信用户列表*/
    JSONObject getWxUserList(JSONObject jsonObject);

}
