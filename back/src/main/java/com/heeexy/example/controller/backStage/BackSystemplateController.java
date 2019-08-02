package com.heeexy.example.controller.backStage;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.SysTemplateService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BackSystemplateController
 * @Description 系统消息模板相关操作
 * @Author Lingling00
 * @DATE 8/2/2019 15:00
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/sysTemplate")
public class BackSystemplateController {
    @Autowired
    private SysTemplateService sysTemplateService;

    /**
     * @description 获取系统消息模板列表
     * @param request
     * @return com.alibaba.fastjson.JSONObject
     **/
    @GetMapping("/getSysTemplateList")
    public JSONObject getSysTemplateList(HttpServletRequest request){
        return sysTemplateService.getSysTemplateList(CommonUtil.request2Json(request));
    }

    /**
     * @description 添加系统消息模板
     * @param request 模板标题 - sysTempTitle，模板内容 - sysTempContent
     * @return 系统消息模板id - sysTempId
     **/
    @PostMapping("/insertSysTemplate")
    public JSONObject insertSysTemplate(HttpServletRequest request){
        return sysTemplateService.insertSysTemplate(CommonUtil.request2Json(request));
    }

    /**
     * @description 修改系统消息模板（包括设置为引导语）
     * @param request 模板标题 - sysTempTitle，模板内容 - sysTempContent
     *                是否为引导语 - isGuide（1为是，0为否），系统消息模板id - sysTempId
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/updateSysTemplate")
    public JSONObject updateSysTemplate(HttpServletRequest request){
        return sysTemplateService.updateSysTemplate(CommonUtil.request2Json(request));
    }

    /**
     * @description 删除系统消息模板
     * @param request 系统消息模板id - sysTempId
     * @return com.alibaba.fastjson.JSONObject
     **/
    @PostMapping("/updateDelTemplate")
    public JSONObject updateDelTemplate(HttpServletRequest request){
        return sysTemplateService.updateDelTemplate(CommonUtil.request2Json(request));
    }
}
