package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxUserService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wxUser")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    /**
     *@description:获取用户列表
     *@param jsonObject(包括用户昵称nickname、最近活跃时间段dateRange,参数都为空时则查询全部用户列表)
     *@return 用户列表(包括用户昵称nickname、用户性别gender、地区region、注册时间createTime、最近活跃时间updateTime)
     */
    @GetMapping("/getWxUserlist")
    public JSONObject getWxUserList(HttpServletRequest request) {
        return wxUserService.getWxUserList(CommonUtil.request2Json(request));
    }
    /**
     *@description:用户登录操作，根据是否授权获取用户信息分为四种情况
     *@param request
     *@return java.lang.String（生成的游客标识符（userId））
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        JSONObject jsonObject = CommonUtil.request2Json(request);
        if (jsonObject.get("unionId")!=null &&jsonObject.get("userId")==null) {
            //首次登录即授权，执行插入微信用户信息操作
        }
        else if(jsonObject.get("unionId")==null &&jsonObject.get("userId")==null){
            //首次登录未授权，执行插入游客信息操作
        }
        else if(jsonObject.get("unionId")!=null&&jsonObject.get("userId")!=null){
            //首次登录未授权，获取游客信息userId，之后登录授权，执行插入微信用户信息操作
        }
        else if(jsonObject.get("unionId")==null&&jsonObject.get("userId")!=null){
            //以游客身份登录
            return wxUserService.insertVisitor(CommonUtil.request2Json(request));
        }
        else {
            return "登录失败！";
        }
        return "123456";
    }

}
