package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.UserRestrictService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lingling
 * @description 用户权限管理功能实现,包括对帖子发布数量的管理和举报次数的管理
 * @date 2019/6/28 0028
 */
@RestController
@RequestMapping("/userRes")
public class BackUserRestrictController {
    @Autowired
    private UserRestrictService userRestrictService;

    /**
     *@description:对用户设置禁言并设定时长
     *@param request
     *@return com.alibaba.fastjson.JSONObject
     */
    @RequiresPermissions("wxUser:updateWxUserRes")
    @PostMapping("/updateWxUserRes")
    public JSONObject updateUser(HttpServletRequest request) {
        return userRestrictService.updateWxUserRes(CommonUtil.request2Json(request));
    }
}
