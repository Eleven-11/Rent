package com.heeexy.example.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.WxReportService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: chens
 * @description: 用户举报控制层
 * @date: 2019-07-29 17:56
 */
@RestController
@RequestMapping("/wxUserReport")
public class WxReportController {

    @Autowired
    private WxReportService wxReportService;

    /**
     * 微信用户举报
     * userId 用户Id
     * targetType 举报对象的类型
     * targetId 举报对象的Id
     * @param request
     * @return
     */
    @RequiresPermissions("wx:report:add")
    @PostMapping("/report")
    public JSONObject userReport(HttpServletRequest request) {
        try {
            return wxReportService.userReport(CommonUtil.request2Json(request));
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_405);
        }
    }

}
